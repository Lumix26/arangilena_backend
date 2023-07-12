package version1.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import version1.demo.models.prodotto.CategoriaE;
import version1.demo.models.prodotto.ProdottoEntrata;
import version1.demo.repositories.CategoriaERepo;
import version1.demo.repositories.ProdottoRepo;
import version1.demo.utils.DTOprodEntrata;
import version1.demo.utils.exception.CategoriaNonPresente;


@Service
public class ProdottoS {

    @Autowired
    private ProdottoRepo prodottoRepo;
    @Autowired
    private CategoriaERepo catRepo;
    
    public void creaProdotto(DTOprodEntrata dto) throws CategoriaNonPresente{
        
        ProdottoEntrata pE = new ProdottoEntrata();
        pE.setNome(dto.getNome());
        pE.setPrezzoBase(dto.getPrezzo());
        pE.setDescrizione(dto.getDescrz());
        
        Optional<CategoriaE> op = catRepo.findByNome(dto.getNome());
        if(!op.isEmpty()){
            CategoriaE categoriaE = op.get();
            pE.setCategoriaE(categoriaE);
        }else{
            throw new CategoriaNonPresente("La categoria indicata non esiste!");
        }

        prodottoRepo.save(pE);
    }
}
