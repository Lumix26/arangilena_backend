package version1.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import version1.demo.models.prodotto.CategoriaE;
import version1.demo.models.prodotto.Prodotto;
import version1.demo.models.prodotto.ProdottoEntrata;
import version1.demo.repositories.CategoriaERepo;
import version1.demo.repositories.ProdottoRepo;
import version1.demo.utils.DTOprodEntrata;
import version1.demo.utils.exception.CategoriaNonPresente;
import version1.demo.utils.exception.ProdottoNonPresente;


@Service
public class ProdottoS {

    @Autowired
    private ProdottoRepo prodottoRepo;
    @Autowired
    private CategoriaERepo catRepo;
    
    @Transactional(readOnly = false, rollbackFor = CategoriaNonPresente.class)
    public void creaProdotto(DTOprodEntrata dto) throws CategoriaNonPresente{
        
        ProdottoEntrata pE = new ProdottoEntrata();
        pE.setNome(dto.getNome());
        pE.setPrezzoBase(dto.getPrezzo());
        pE.setDescrizione(dto.getDescrz());
        pE.setMax_scorte(dto.getScorte());
        
        Optional<CategoriaE> op = catRepo.findByNome(dto.getNomeCategoria());

        if(!op.isEmpty()){
            CategoriaE categoriaE = op.get();
            pE.setCategoriaE(categoriaE);
        }else{
            throw new CategoriaNonPresente("La categoria indicata non esiste!");
        }
        prodottoRepo.save(pE);
    }

    @Transactional(readOnly = false, rollbackFor = ProdottoNonPresente.class)
    public void eliminaProdotto(String nome) throws ProdottoNonPresente{
        Optional<Prodotto> op = prodottoRepo.findByNome(nome);
        if(!op.isEmpty()){
            Prodotto p = op.get();
            prodottoRepo.deleteById(p.getId());
        }else{
            throw new ProdottoNonPresente("Il prodotto con nome "+nome+" non è presente nel db");
        }
        
    }
}
