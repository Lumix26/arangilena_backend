package version1.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import version1.demo.models.utente.Acquirente;
import version1.demo.repositories.AcquirenteRepo;
import version1.demo.repositories.IndirizzoRepo;
import version1.demo.repositories.RecapitoRepo;
import version1.demo.repositories.UtenteRepo;
import version1.demo.utils.exception.AcquirenteNonPresente;

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
    public void deleteAcquirente(Long id) throws AcquirenteNonPresente{
        if(utenteRepo.existsById(id)){
            utenteRepo.deleteById(id);
        }else{
            throw new AcquirenteNonPresente("L'acquirente identificato dall'id "+id+" non è presente.");
        }
    }

    @Transactional(readOnly = true)
    public List<Acquirente> listAcquirenti(){
        return aRepo.findAll();
    }

    
}
