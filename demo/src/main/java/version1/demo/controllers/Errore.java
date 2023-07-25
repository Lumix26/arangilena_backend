package version1.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/errore")
public class Errore {
    
    @GetMapping("/scorte")
    public ModelAndView qntNonDisponibile(){
        return new ModelAndView("ErrorPage.html", "messaggio", "Quantità scorte non disponibili");
    }
}
