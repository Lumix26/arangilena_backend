package version1.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import version1.demo.models.prodotto.Aziendali;
import version1.demo.models.prodotto.ProdottoEntrata;
import version1.demo.repositories.ProdottoRepo;


@Service
public class ProdottoS {

    @Autowired
    private ProdottoRepo prodottoRepo;
    
    public void creaProdotto(){
        ProdottoEntrata pe = new ProdottoEntrata();
        pe.setNome("clementina");
        pe.setPrezzoBase(20);
        pe.setDescrizione("ciaooo");
        pe.setProdotto(Aziendali.INGROSSO);

        prodottoRepo.save(pe);
        
    }
}
