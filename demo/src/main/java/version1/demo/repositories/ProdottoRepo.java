package version1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.prodotto.Prodotto;

public interface ProdottoRepo extends JpaRepository<Prodotto,Long>{
    
}
