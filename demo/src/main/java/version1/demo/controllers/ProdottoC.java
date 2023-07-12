package version1.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import version1.demo.services.ProdottoS;

@RestController
@RequestMapping("/prodotto")
public class ProdottoC {
    
    @Autowired
    private ProdottoS pS;

    /*@GetMapping
    public String prova(){
        pS.creaProdotto();
        return "<h1> prodotto inserito </h1>";
    }*/
}
