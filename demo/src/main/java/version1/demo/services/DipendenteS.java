package version1.demo.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import version1.demo.models.security.ERole;
import version1.demo.models.security.Role;
import version1.demo.models.utente.Dipendente;
import version1.demo.models.utente.Indirizzo;
import version1.demo.models.utente.Ruolo;
import version1.demo.repositories.IndirizzoRepo;
import version1.demo.repositories.RoleRepo;
import version1.demo.repositories.UtenteRepo;
import version1.demo.utils.DTODipendente;

@Service
public class DipendenteS {
    @Autowired
    private UtenteRepo uRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IndirizzoRepo iRepo;
    @Autowired
    private RoleRepo roleRepo;
    
    @Transactional(readOnly = false)
    public void createDipendente(DTODipendente dtoDip){

        Dipendente d = new Dipendente();
        d.setUsername(dtoDip.getUsername());
        d.setPassword(passwordEncoder.encode(dtoDip.getPassword()));
        d.setNome(dtoDip.getNome());
        d.setCognome(dtoDip.getCognome());

        Indirizzo i = new Indirizzo();
        i.setCitta(dtoDip.getCitta());
        i.setCap(dtoDip.getCap());
        i.setVia(dtoDip.getVia());
        i.setN_civico(dtoDip.getNumeroCivico());
        iRepo.save(i);

        d.setIndirizzo(i);
        d.setTelefono(dtoDip.getTelefono());
        d.setStipendio(dtoDip.getStipendio());
        String tmp = dtoDip.getRuolo();

        /* dovrei usare il pattern State ma non so come si usa */
        if(tmp.equals(Ruolo.TITOLARE.name())){
            d.setRuolo(Ruolo.TITOLARE);
        }
        if(tmp.equals(Ruolo.SOCIO.name())){
            d.setRuolo(Ruolo.SOCIO);
        }
        if(tmp.equals(Ruolo.SUPERVISORE_LINEA.name())){
            d.setRuolo(Ruolo.SUPERVISORE_LINEA);
        }
        if(tmp.equals(Ruolo.CONTABILE.name())){
            d.setRuolo(Ruolo.CONTABILE);
        }
        if(tmp.equals(Ruolo.ADDETTO_VENDITE.name())){
            d.setRuolo(Ruolo.ADDETTO_VENDITE);
        }
        if(tmp.equals(Ruolo.ADDETTO_DEPOSITO.name())){
            d.setRuolo(Ruolo.ADDETTO_DEPOSITO);
        }
        if(tmp.equals(Ruolo.RESPONSABILE_QUALITA.name())){
            d.setRuolo(Ruolo.RESPONSABILE_QUALITA);
        }
        if(tmp.equals(Ruolo.OPERAIO.name())){
            d.setRuolo(Ruolo.OPERAIO);
        }
        if(tmp.equals(Ruolo.CONTROLLORE.name())){
            d.setRuolo(Ruolo.CONTROLLORE);
        }

        HashSet<Role> ruoli = new HashSet<>();
        if (dtoDip.getRuolo().equals(Ruolo.TITOLARE.name()) || dtoDip.getRuolo().equals(Ruolo.SOCIO.name())){
            ruoli.add(roleRepo.findByNome(ERole.TOP_LEVEL).get());
            ruoli.add(roleRepo.findByNome(ERole.MEDIUM_LEVEL).get());
            ruoli.add(roleRepo.findByNome(ERole.LOW_LEVEL).get());
            ruoli.add(roleRepo.findByNome(ERole.GUEST).get());   
        } else if(dtoDip.getRuolo().equals(Ruolo.OPERAIO.name()) || dtoDip.getRuolo().equals(Ruolo.CONTROLLORE)){
            ruoli.add(roleRepo.findByNome(ERole.LOW_LEVEL).get());
            ruoli.add(roleRepo.findByNome(ERole.GUEST).get()); 

        }else{
            ruoli.add(roleRepo.findByNome(ERole.MEDIUM_LEVEL).get());
            ruoli.add(roleRepo.findByNome(ERole.LOW_LEVEL).get());
            ruoli.add(roleRepo.findByNome(ERole.GUEST).get());
        }
        d.setRuoli(ruoli);

        uRepo.save(d);
    }
}
