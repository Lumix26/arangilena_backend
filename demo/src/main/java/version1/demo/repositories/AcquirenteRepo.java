package version1.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.utente.Acquirente;

public interface AcquirenteRepo extends JpaRepository<Acquirente, Long>{
    List<Acquirente> findAll();
    boolean existsByPiva(String piva);
}
