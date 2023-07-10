package version1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.utente.Recapito;

public interface RecapitoRepo extends JpaRepository<Recapito,Long>{
    
}
