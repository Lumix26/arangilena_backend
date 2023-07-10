package version1.demo.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import version1.demo.models.utente.Acquirente;
import version1.demo.models.utente.Dipendente;
import version1.demo.models.utente.Indirizzo;
import version1.demo.models.utente.Recapito;
import version1.demo.models.utente.Ruolo;
import version1.demo.repositories.AcquirenteRepo;
import version1.demo.repositories.IndirizzoRepo;
import version1.demo.repositories.RecapitoRepo;
import version1.demo.repositories.UtenteRepo;

@Service
public class AcquirenteS {

    @Autowired
    private UtenteRepo utenteRepo;
    @Autowired
    private AcquirenteRepo aRepo;
    @Autowired
    private IndirizzoRepo indirizzoRepo;
    @Autowired
    private RecapitoRepo recapitoRepo;


    @Transactional(readOnly = false)
    public void createAcquirente(Acquirente acquirente){
        if (!aRepo.existsByPiva(acquirente.getPiva())){
            indirizzoRepo.save(acquirente.getIndirizzo());
            recapitoRepo.save(acquirente.getRecapito());
            utenteRepo.save(acquirente);
        }
    }

    @Transactional(readOnly = false)
    public boolean deleteAcquirente(Long id){
        if(utenteRepo.existsById(id)){
            utenteRepo.deleteById(id);
            return true;
        }return false;
    }

    @Transactional(readOnly = true)
    public List<Acquirente> listAcquirenti(){
        return aRepo.findAll();
    }

    
}
