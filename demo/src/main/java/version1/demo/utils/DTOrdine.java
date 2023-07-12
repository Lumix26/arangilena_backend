package version1.demo.utils;


import java.util.LinkedList;

import version1.demo.models.ordine.DettaglioOrdine;

public class DTOrdine {
    private String descrizione;
    private String categoria;
    private LinkedList<DettaglioOrdine> dettagliProd;
    private Long acquirente;
    
    public DTOrdine() {
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    public Long getAcquirente() {
        return acquirente;
    }

    public void setAcquirente(Long acquirente) {
        this.acquirente = acquirente;
    }

    

    public LinkedList<DettaglioOrdine> getDettagliProd() {
        return dettagliProd;
    }

    public void setDettagliProd(LinkedList<DettaglioOrdine> dettagliProd) {
        this.dettagliProd = dettagliProd;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    

    

  

    
}
