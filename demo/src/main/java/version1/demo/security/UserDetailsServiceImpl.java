package version1.demo.security;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import version1.demo.models.security.Role;
import version1.demo.models.utente.Utente;
import version1.demo.repositories.UtenteRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UtenteRepo uRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Utente> op = uRepo.findByUsername(username);
        if(!op.isEmpty()){
            Utente u = op.get();
            return new User(
                u.getUsername(),
                u.getPassword(),
                getPermessi(u.getRuoli())
            );
        }else{
            throw new UsernameNotFoundException("lo username "+username+" non è associato a nessun utente");
        }
    }

    private Collection<? extends GrantedAuthority> getPermessi(Collection<Role> ruoli){
        return ruoli.stream().map(
            ruolo -> new SimpleGrantedAuthority(ruolo.getNome().name())
        ).collect(Collectors.toList());
    }
    
}
