package version1.demo.repositories;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import version1.demo.models.prodotto.CategoriaE;

public interface CategoriaERepo extends JpaRepository<CategoriaE,Long>{

    @Query("Select c From CategoriaE c WHERE e.nome = :nome")
    Optional<CategoriaE> findByNome(@Param("nome") String nome);
}
