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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/addCart")
    public void aggiungiAlCarrello(@RequestBody DTOProdotto prod){
        Optional<Prodotto> op = pRep.findByNome(prod.getNome());
        if(!op.isEmpty()){
            Prodotto p = op.get();
            Map.Entry<Long,Integer> e = Map.entry(p.getId(), prod.getQnt());
            cart.aggiungiProdotto(e);
        }
    }

    @PostMapping("/deleteFromCart")
    public void eliminaDalCarrello(@RequestBody DTOProdotto prod){
        Optional<Prodotto> op = pRep.findByNome(prod.getNome());
        if(!op.isEmpty()){
            Prodotto p = op.get();
            Map.Entry<Long,Integer> e = Map.entry(p.getId(), prod.getQnt());
            cart.eliminaProdotto(e);
        }
    }

    /*@GetMapping("/mostraCarrello")
    public ModelAndView prodottiCarrello(){
        return new ModelAndView("Carrello.html", "carrello", cart.getCarrello());
    }*/

    @GetMapping("/mostraCarrello")
    public ModelAndView prodottiCarrello(){
        HashMap<Long,Integer> c = cart.getCarrello();
        LinkedList<ProdottoCart> prodotti = new LinkedList<>();

        for(Entry<Long,Integer> e : c.entrySet()){
            Optional<Prodotto> op = pRep.findById(e.getKey());
            if(!op.isEmpty()){
                Prodotto p = op.get();
                ProdottoCart pCart = new ProdottoCart();
                pCart.setNome(p.getNome());
                pCart.setQnt(e.getValue());
                pCart.setPrezzo_parziale(p.getPrezzoBase()*e.getValue());
                prodotti.add(pCart);
            }
        }
        return new ModelAndView("Carrello.html", "prodottiCart", prodotti);
    }

    @PostMapping("/inviaOrdine")
    public void inviaOrdina(@RequestParam("descrizione") String descr){
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
                if( e.getValue() <= pE.getMax_scorte())
                    dto.setQnt(e.getValue());
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
    }
}
