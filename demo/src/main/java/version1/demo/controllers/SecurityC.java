package version1.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provaSicurezza")
public class SecurityC {
    @GetMapping
    public String prova(){
        return "<h1> sono stato autenticato! </h1>";
    }
}
