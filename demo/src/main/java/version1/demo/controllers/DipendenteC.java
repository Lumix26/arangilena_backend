package version1.demo.controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import version1.demo.models.security.ERole;
import version1.demo.models.security.Role;
import version1.demo.models.utente.Dipendente;
import version1.demo.models.utente.Indirizzo;
import version1.demo.models.utente.Ruolo;
import version1.demo.repositories.IndirizzoRepo;
import version1.demo.repositories.UtenteRepo;
import version1.demo.services.DipendenteS;
import version1.demo.utils.DTODipendente;

@RestController
@RequestMapping("/produzione/dipendenteAPI")
public class DipendenteC {

    @Autowired
    private DipendenteS dS;
    

    @PostMapping("/createDipendente")
    private void createDipendente(@RequestBody DTODipendente dtoDip){
        dS.createDipendente(dtoDip);
    }
}
