package version1.demo.models.ordine;


import java.util.Collection;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import version1.demo.models.utente.Acquirente;

@Entity
public class Entrata extends Ordine{
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoOrdine stato;

    @Column(nullable = false, length = 15)
    private String categoria;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_acquirente")
    @JsonIgnore
    private Acquirente acquirente;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ordine")
    private Collection<DettaglioOrdine> dettagli = new LinkedList<>();

    public Entrata() {
    }


    /* Getter & Setter */



    public Acquirente getAcquirente() {
        return acquirente;
    }

    public void setAcquirente(Acquirente acquirente) {
        this.acquirente = acquirente;
    }


    public Collection<DettaglioOrdine> getDettagli() {
        return dettagli;
    }


    public void setDettagli(Collection<DettaglioOrdine> dettagli) {
        this.dettagli = dettagli;
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

    
    

    
}
