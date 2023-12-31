package version1.demo.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import version1.demo.models.ordine.DettaglioOrdine;
import version1.demo.models.prodotto.CategoriaE;
import version1.demo.models.prodotto.Prodotto;
import version1.demo.models.prodotto.ProdottoEntrata;
import version1.demo.models.utente.Utente;
import version1.demo.repositories.DettOrdineRepo;
import version1.demo.repositories.ProdottoEntrataRepo;
import version1.demo.repositories.ProdottoRepo;
import version1.demo.repositories.UtenteRepo;
import version1.demo.services.OrdineS;
import version1.demo.utils.Carrello;
import version1.demo.utils.DTOProdotto;
import version1.demo.utils.DTOrdine;
import version1.demo.utils.ProdottoCart;
import version1.demo.utils.exception.QntNonDisponibile;

@RestController
@RequestMapping("/carrelloAPI")
public class CarrelloC {
    @Autowired
    private Carrello cart;
    @Autowired
    private ProdottoRepo pRep;
    @Autowired
    private ProdottoEntrataRepo pERep;
    @Autowired
    private OrdineS ordineS;
    @Autowired
    private UtenteRepo uRepo;
    @Autowired
    private DettOrdineRepo dttRepo;

    private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @PostMapping("/addCart")
    public RedirectView aggiungiAlCarrello(@RequestBody DTOProdotto prod){
        Optional<Prodotto> op = pRep.findByNome(prod.getNome());
        if(!op.isEmpty()){
            Prodotto p = op.get();
            Map.Entry<Long,Integer> e = Map.entry(p.getId(), prod.getQnt());
            cart.aggiungiProdotto(e);
        }
        return new RedirectView("/vetrina/prodottiOfferti");
    }

    @PostMapping("/deleteFromCart")
    public RedirectView eliminaDalCarrello(@RequestBody DTOProdotto prod){
        Optional<Prodotto> op = pRep.findByNome(prod.getNome());
        if(!op.isEmpty()){
            Prodotto p = op.get();
            Map.Entry<Long,Integer> e = Map.entry(p.getId(), prod.getQnt());
            cart.eliminaProdotto(e);
        }
        return new RedirectView("/carrelloAPI/mostraCarrello");
    }

    /*@GetMapping("/mostraCarrello")
    public ModelAndView prodottiCarrello(){
        return new ModelAndView("Carrello.html", "carrello", cart.getCarrello());
    }*/

    @GetMapping("/mostraCarrello")
    public ModelAndView prodottiCarrello(){
        HashMap<Long,Integer> c = cart.getCarrello();
        LinkedList<ProdottoCart> prodotti = new LinkedList<>();
        double costoOrdine = 0;
        ModelAndView modelAndView = new ModelAndView("Carrello.html");

        for(Entry<Long,Integer> e : c.entrySet()){
            Optional<Prodotto> op = pRep.findById(e.getKey());
            if(!op.isEmpty()){
                Prodotto p = op.get();
                ProdottoCart pCart = new ProdottoCart();
                pCart.setNome(p.getNome());
                pCart.setQnt(e.getValue());
                pCart.setPrezzo_parziale(p.getPrezzoBase()*e.getValue());
                prodotti.add(pCart);
                costoOrdine += p.getPrezzoBase()*e.getValue();
            }
        }
        modelAndView.addObject("prodottiCart", prodotti);
        modelAndView.addObject("totale", costoOrdine);
        return modelAndView;
    }

    @PostMapping("/inviaOrdine")
    public RedirectView inviaOrdina(@RequestParam("descrizione") String descr) throws QntNonDisponibile{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        HashMap<Long,Integer> prodQnt = cart.getCarrello();
        LinkedList<DettaglioOrdine> prodotti = new LinkedList<>();
        int countINGROSSO = 0;
        int countBEVANDA = 0;
        DTOrdine dtOrdine = new DTOrdine();
        

        for( Entry<Long,Integer> e : prodQnt.entrySet()){
            Optional<ProdottoEntrata> op = pERep.findById(e.getKey());
            if(!op.isEmpty()){
                ProdottoEntrata pE = op.get();
                CategoriaE c = pE.getCategoriaE();
                if( c.getNome().equals("INGROSSO") ){
                    countINGROSSO++;
                }else{
                    countBEVANDA++;
                }
                DettaglioOrdine dto = new DettaglioOrdine();
                dto.setProdotto(pE);
                if( e.getValue() <= pE.getMax_scorte()){
                    dto.setQnt(e.getValue());
                }else{
                    cart.svuotaCarrello();
                    return new RedirectView("/errore/scorte");
                }
                Optional<Prodotto> p = pRep.findById(e.getKey());
                Prodotto pp = p.get();
                int scorte_nuove = pp.getMax_scorte() - e.getValue();
                pp.setMax_scorte(scorte_nuove);
                pRep.save(pp);
                dttRepo.save(dto);
                prodotti.add(dto);
            }
        }
        if(countINGROSSO == prodQnt.size()){
            dtOrdine.setCategoria("INGROSSO");
        }
        if(countBEVANDA == prodQnt.size()){
            dtOrdine.setCategoria("BEVANDE");
        }
        dtOrdine.setDettagliProd(prodotti);
        dtOrdine.setCategoria("MISTO");
        dtOrdine.setDescrizione(descr);
        if(auth.isAuthenticated()){
            String username = auth.getName();
            Optional<Utente> op = uRepo.findByUsername(username);
            if(op.isPresent()){
                Utente u = op.get();
                dtOrdine.setAcquirente(u.getId());
            }
        }
        

        ordineS.creaOrdine(dtOrdine);
        cart.svuotaCarrello();
        return new RedirectView("/carrelloAPI/mostraCarrello");
    }

    @PostMapping("/logout")
    public void logOut(HttpServletRequest request, HttpServletResponse response, Authentication auth){
        this.logoutHandler.logout(request, response, auth);
    }
}
