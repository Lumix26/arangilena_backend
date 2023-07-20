package version1.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.utente.Utente;


public interface UtenteRepo extends JpaRepository<Utente,Long>{
    
    Optional<Utente> findByUsername(String username);
}
