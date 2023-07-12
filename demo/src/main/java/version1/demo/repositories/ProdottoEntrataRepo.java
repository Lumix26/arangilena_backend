package version1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.prodotto.ProdottoEntrata;

public interface ProdottoEntrataRepo extends JpaRepository<ProdottoEntrata,Long>{
    
}
