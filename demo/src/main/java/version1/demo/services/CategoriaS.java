package version1.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import version1.demo.models.prodotto.CategoriaE;
import version1.demo.repositories.CategoriaERepo;

@Service
public class CategoriaS {

    @Autowired
    private CategoriaERepo cRepo; 

    public void creaCategoria(String nome){
        CategoriaE c = new CategoriaE();
        c.setNome(nome);
        cRepo.save(c);
    }
}
