package version1.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import version1.demo.services.OrdineS;
import version1.demo.utils.DTOStatoOrdine;
import version1.demo.utils.DTOrdine;

@RestController
@RequestMapping("/produzione/ordineEntrataAPI")
public class OrdineC {
    
    @Autowired
    private OrdineS ordineS;

    @GetMapping
    public ModelAndView listaOrdini(Model m){
        return new ModelAndView("ListaOrdini.html", "ordini", ordineS.listaOrdini());
    }

    @PostMapping("/aggiornaStato")
    public void aggiornaStatoOrdine(@RequestBody DTOStatoOrdine stato){
        ordineS.updateStatusOrder(stato);
    }

    
}
