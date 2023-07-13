package version1.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import version1.demo.services.ProdottoS;
import version1.demo.utils.DTOprodEntrata;
import version1.demo.utils.exception.CategoriaNonPresente;

@RestController
@RequestMapping("/prodottiAPI")
public class ProdottoC {
    
    @Autowired
    private ProdottoS pS;

    @PostMapping("/createProd")
    public void aggiungiProdotto(@RequestBody DTOprodEntrata dto){
        try {
            pS.creaProdotto(dto);
        } catch (CategoriaNonPresente e) {
           throw e;
        }
    }

    @PostMapping("/deleteProd")
    public void eliminaProdotto( @RequestBody String nome){

    }
}
