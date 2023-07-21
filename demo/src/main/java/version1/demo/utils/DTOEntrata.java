package version1.demo.utils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import version1.demo.models.ordine.DettaglioOrdine;
import version1.demo.models.ordine.StatoOrdine;
import version1.demo.models.utente.Acquirente;

public class DTOEntrata {
    private Long id;
    private LocalDate data;
    private String descrz;
    private StatoOrdine stato;
    private String categoria;
    private Acquirente cliente;
    private Collection<DettaglioOrdine> dettagli;

    public DTOEntrata() {
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescrz() {
        return descrz;
    }

    public void setDescrz(String descrz) {
        this.descrz = descrz;
    }

    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Acquirente getCliente() {
        return cliente;
    }

    public void setCliente(Acquirente cliente) {
        this.cliente = cliente;
    }

    public Collection<DettaglioOrdine> getDettagli() {
        return dettagli;
    }

    public void setDettagli(Collection<DettaglioOrdine> dettagli) {
        this.dettagli = dettagli;
    }

    
}
