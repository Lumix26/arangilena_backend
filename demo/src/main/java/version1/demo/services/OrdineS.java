package version1.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import version1.demo.models.ordine.DettaglioOrdine;
import version1.demo.models.ordine.Entrata;
import version1.demo.models.ordine.Ordine;
import version1.demo.models.ordine.StatoOrdine;
import version1.demo.models.utente.Acquirente;
import version1.demo.repositories.AcquirenteRepo;
import version1.demo.repositories.EntrataRepo;
import version1.demo.repositories.OrdineRepo;
import version1.demo.repositories.ProdottoRepo;
import version1.demo.utils.DTOStatoOrdine;
import version1.demo.utils.DTOrdine;

@Service
public class OrdineS {
    
    @Autowired
    private OrdineRepo ordineRepo;
    @Autowired
    private EntrataRepo entrataRepo;
    @Autowired
    private ProdottoRepo prodottoRepo;
    @Autowired
    private AcquirenteRepo aRepo;



    @Transactional(readOnly = true)
    public List<Entrata> listaOrdini(){
        return entrataRepo.findAll();
    }

    @Transactional(readOnly = false)
    public void creaOrdine(DTOrdine ordine){
        
        Entrata oe = new Entrata();

        oe.setDescrizione(ordine.getDescrizione());
        
        Optional<Acquirente> op = aRepo.findById(ordine.getAcquirente());
        if(!op.isEmpty()){
            Acquirente a = op.get();
            oe.setAcquirente(a);
        }
        oe.setDettagli(ordine.getDettagliProd());
        oe.setData(LocalDate.now());
        oe.setStato(StatoOrdine.ATTESA);

        ordineRepo.save(oe);
    }

    public void updateStatusOrder(DTOStatoOrdine stato){

        Optional<Entrata> op = entrataRepo.findById(stato.getId_ordine());
        if(!op.isEmpty()){
            Entrata oe = op.get();
            if(stato.getStato().equals("LAVORAZIONE")){
                oe.setStato(StatoOrdine.LAVORAZIONE);
            }
            if(stato.getStato().equals("SPEDITO")){
                oe.setStato(StatoOrdine.SPEDITO);
            }
            ordineRepo.save(oe);
        }
    }

}
