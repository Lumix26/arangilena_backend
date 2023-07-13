package version1.demo.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.prodotto.CategoriaE;

public interface CategoriaERepo extends JpaRepository<CategoriaE,Long>{

    Optional<CategoriaE> findByNome(String nome);
}
