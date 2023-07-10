package version1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.utente.Indirizzo;

public interface IndirizzoRepo extends JpaRepository<Indirizzo,Long>{
    
}
