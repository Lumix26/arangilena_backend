package version1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.prodotto.Prodotto;
import java.util.List;


public interface ProdottoRepo extends JpaRepository<Prodotto,Long>{
    List<Prodotto> findByNome(String nome);
}
