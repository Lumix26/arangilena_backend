package version1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.ordine.Entrata;

public interface EntrataRepo extends JpaRepository<Entrata,Long>{
    

}
