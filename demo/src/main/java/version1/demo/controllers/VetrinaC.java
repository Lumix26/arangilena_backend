package version1.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class VetrinaC {
    
    public ModelAndView home() {
        return new ModelAndView("Vetrina.html","null",null);
    }
}
