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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public void createNewAcquirente(@RequestBody DTOAcquirente dtoA){
        Set<Role> ruoli = new HashSet<>();
        Acquirente a = new Acquirente();
        a.setUsername(dtoA.getUsername());
        a.setPassword(passwordEncoder.encode(dtoA.getPassword()));
        a.setPiva(dtoA.getPiva());
        a.setRagioneSociale(dtoA.getRagioneSociale());
        Indirizzo i = new Indirizzo(dtoA.getCitta(), dtoA.getCap(), dtoA.getVia(), dtoA.getNumeroCivico());
        Recapito r = new Recapito(dtoA.getMail(),dtoA.getFax(),dtoA.getTelefono());
        a.setIndirizzo(i);
        a.setRecapito(r);
        ruoli.add(roleRepo.findByNome(ERole.GUEST).get());
        a.setRuoli(ruoli);
        
        aS.createAcquirente(a);
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
