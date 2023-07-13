package version1.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
import version1.demo.utils.exception.AcquirenteNonPresente;

@RestController
@RequestMapping("/acquirenteAPI")
public class AcquirenteC {

    @Autowired
    private AcquirenteS aS;
    
    /*  
     * Homepage che mostra le operazioni effettuabili sugli acquirenti.
     */
    @GetMapping
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
    public ModelAndView createNewAcquirente(@RequestBody DTOAcquirente dtoA){
        Acquirente a = new Acquirente();
        a.setUsername(dtoA.getUsername());
        a.setPassword(dtoA.getPassword());
        a.setPiva(dtoA.getPiva());
        a.setRagioneSociale(dtoA.getRagioneSociale());
        Indirizzo i = new Indirizzo(dtoA.getCitta(), dtoA.getCap(), dtoA.getVia(), dtoA.getNumeroCivico());
        Recapito r = new Recapito(dtoA.getMail(),dtoA.getFax(),dtoA.getTelefono());
        a.setIndirizzo(i);
        a.setRecapito(r);

        try {
            aS.createAcquirente(a);
            return new ModelAndView("ListaAcquirenti.html", "acquirenti", aS.listAcquirenti());
        } catch (RuntimeException e) {
            System.err.println("Errore nella creazione di un'acquirente");
            return new ModelAndView("ErrorCreazione.html", "null", null);
        }
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
