package version1.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import version1.demo.services.CategoriaS;

@RestController
@RequestMapping("/categoriaAPI")
public class CategoriaC {
    @Autowired
    private CategoriaS catS;
    
    @PostMapping("/addCategoria")
    public void aggiungiCategoria(@RequestBody String nome){
        catS.creaCategoria(nome);
    }
}
