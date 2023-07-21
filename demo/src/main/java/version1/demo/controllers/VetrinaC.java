package version1.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/vetrina")
public class VetrinaC {
    
    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("Vetrina.html", "null", null);
    }

    @PreAuthorize("hasRole('GUEST','LOW_LEVEL')")
    @GetMapping("/prodottiOfferti")
    public ModelAndView prodotti(){
        return new ModelAndView("ProdottiOfferti.html", "null", null);
    }

    @GetMapping("/chiSiamo")
    public String chiSiamo(){
        return "chi siamo!";
    }


}
