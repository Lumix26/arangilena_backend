package version1.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import version1.demo.services.AcquirenteS;

@RestController
public class Home {

    @Autowired
    private AcquirenteS acquirenteS;
    
    @GetMapping("/")
    public String home (){
        acquirenteS.saveUtente();
        return "<H1> Ho salvato l'utente! </H1>";
    }
}
