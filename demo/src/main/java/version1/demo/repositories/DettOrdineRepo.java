package version1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.ordine.DettaglioOrdine;

public interface DettOrdineRepo extends JpaRepository<DettaglioOrdine,Long>{
    
}
