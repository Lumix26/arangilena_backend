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

    @Transactional
    public void saveUtente(){
       Recapito r = new Recapito("marco.valerioti01@gmail.com", "12345", "3278787320");
       recapitoRepo.save(r);

       Indirizzo i = new Indirizzo("Polistena", "89024", "via delle fabbriche", 11);
       indirizzoRepo.save(i);
       Acquirente a = new Acquirente();
       
       a.setUsername("Lumi");
       a.setPassword("1234");
       a.setRagioneSociale("Marcosoft");
       a.setPiva("piva1");
       a.setRecapito(r);
       a.setIndirizzo(i);

       utenteRepo.save(a);

       Indirizzo i1 = new Indirizzo("Melicucco", "89036", "Contrada monacelle", 2);
       indirizzoRepo.save(i1);

       Dipendente d = new Dipendente();
       d.setUsername("Grub");
       d.setPassword("uspok");
       d.setNome("Alessandro");
       d.setCognome("Galata");
       d.setIndirizzo(i1);
       d.setRuolo(Ruolo.SOCIO);
       d.setStipendio(1890.50);
       d.setTelefono("3278989210");

       utenteRepo.save(d);
    }

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
