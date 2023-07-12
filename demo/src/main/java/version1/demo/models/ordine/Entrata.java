package version1.demo.models.ordine;


import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import version1.demo.models.utente.Acquirente;

@Entity
public class Entrata extends Ordine{

    @Column(nullable = false, length = 15)
    private String categoria;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_acquirente")
    @JsonIgnore
    private Acquirente acquirente;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ordine")
    private LinkedList<DettaglioOrdine> dettagli = new LinkedList<>();

    public Entrata() {
    }


    /* Getter & Setter */



    public Acquirente getAcquirente() {
        return acquirente;
    }

    public void setAcquirente(Acquirente acquirente) {
        this.acquirente = acquirente;
    }


    public LinkedList<DettaglioOrdine> getDettagli() {
        return dettagli;
    }


    public void setDettagli(LinkedList<DettaglioOrdine> dettagli) {
        this.dettagli = dettagli;
    }

    
    

    
}
