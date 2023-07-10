package version1.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import version1.demo.models.utente.Acquirente;
import version1.demo.models.utente.Indirizzo;
import version1.demo.models.utente.Recapito;
import version1.demo.services.AcquirenteS;
import version1.demo.utils.DTOAcquirente;

@RestController
@RequestMapping("/acquirenteAPI")
public class AcquirenteC {

    @Autowired
    private AcquirenteS aS;
    
    /*  
     * Homepage che mostra le operazioni effettuabili sugli acquirenti.
     */
    @GetMapping("/homepage")
    public ModelAndView acquirenteHome(Model m) {
        return new ModelAndView("AcquirenteHome.html", "null", null);
    }

    /*
     * Pagina che visualizza il form di creazione di un nuovo acquirente.
     */
    @GetMapping("/create-view")
    public ModelAndView viewNewAcquirente(Model m){
        return new ModelAndView("CreaAcquirente.html", "null", null);
    }

    @PostMapping("/createAcquirente")
    public void createNewAcquirente(@RequestBody DTOAcquirente dtoA){
        System.out.println("--------------------"+dtoA.toString()+"----------------");
        System.out.println(dtoA.getIndirizzo().toString());
        System.out.println(dtoA.getRecapiti().toString());
        Acquirente a = new Acquirente();
        a.setUsername(dtoA.getUsername());
        a.setPassword(dtoA.getPassword());

        Indirizzo i = dtoA.getIndirizzo();
        a.setIndirizzo(i);

        Recapito r = dtoA.getRecapiti();
        a.setRecapito(r);
        a.setPiva(dtoA.getPiva());
        a.setRagioneSociale(dtoA.getRagioneSociale());
        aS.createAcquirente(a);
    }


    @GetMapping("/delete-view")
    public ModelAndView viewDeleteAcquirente(Model m){
        return new ModelAndView("EliminaAcquirente.html", "null", null);
    }

    @PostMapping("/deleteAcquirente")
    public void deleteAcquirente (@RequestBody Long id) {
        aS.deleteAcquirente(id);
    }

    
    @GetMapping("/listaAcquirenti")
    public ModelAndView viewVisualizeAcquirenti(Model m){
        return new ModelAndView("ListaAcquirente.html", "null", null);
    }

}
