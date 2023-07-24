package version1.demo.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import version1.demo.models.security.ERole;
import version1.demo.models.security.Role;
import version1.demo.models.utente.Acquirente;
import version1.demo.models.utente.Indirizzo;
import version1.demo.models.utente.Recapito;
import version1.demo.repositories.RoleRepo;
import version1.demo.services.AcquirenteS;
import version1.demo.utils.DTOAcquirente;
import version1.demo.utils.exception.AcquirenteNonPresente;

@RestController
@RequestMapping("/produzione/customerAPI")
public class AcquirenteC {

    @Autowired
    private AcquirenteS aS;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /*  
     * Homepage che mostra le operazioni effettuabili sugli acquirenti.
     */
    @GetMapping
    public String acquirenteHome(Model m) {
        //return new ModelAndView("AcquirenteHome.html", "null", null);
        return "acquirente API";
    }

    /*
     * Pagina che visualizza il form di creazione di un nuovo acquirente.
     */
    @GetMapping("/create-view")
    public String viewNewAcquirente(Model m){
        //return new ModelAndView("CreaAcquirente.html", "null", null);
        return "crea acquirente";
    }

    @PostMapping("/createCustomer")
    public RedirectView createNewAcquirente(@RequestParam("ragione")String rs,@RequestParam("piva")String piva,
        @RequestParam("via")String via, @RequestParam("civico")int civico,@RequestParam("citta")String citta,
            @RequestParam("cap")String cap,@RequestParam("email")String email,@RequestParam("telefono")String tel,
                @RequestParam("fax")String fax,@RequestParam("username")String username,@RequestParam("password")String psw){

        Set<Role> ruoli = new HashSet<>();
        Acquirente a = new Acquirente();
        a.setUsername(username);
        a.setPassword(passwordEncoder.encode(psw));
        a.setPiva(piva);
        a.setRagioneSociale(rs);
        Indirizzo i = new Indirizzo(citta, cap, via, civico);
        Recapito r = new Recapito(email,fax,tel);
        a.setIndirizzo(i);
        a.setRecapito(r);
        ruoli.add(roleRepo.findByNome(ERole.GUEST).get());
        a.setRuoli(ruoli);
        
        aS.createAcquirente(a);
        return new RedirectView("/vetrina");
    }


    @GetMapping("/delete-view")
    public ModelAndView viewDeleteAcquirente(Model m){
        return new ModelAndView("EliminaAcquirente.html", "null", null);
    }

    @PostMapping("/deleteAcquirente")
    public ModelAndView deleteAcquirente (@RequestBody Long id) {
        try {
            aS.deleteAcquirente(id);
            return new ModelAndView("ListaAcquirenti.html", "acquirenti", aS.listAcquirenti());
        } catch (AcquirenteNonPresente e) {
            return new ModelAndView("ErrorCancellazione.html", "null", null);
        }
    }

    
    @GetMapping("/listaAcquirenti")
    public ModelAndView viewVisualizeAcquirenti(Model m){
        return new ModelAndView("ListaAcquirenti.html", "acquirenti", aS.listAcquirenti());
    }

}
