package version1.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import version1.demo.models.security.ERole;
import version1.demo.models.security.Role;



public interface RoleRepo extends JpaRepository<Role,Long>{
    Optional<Role> findByNome(ERole nome);
}
