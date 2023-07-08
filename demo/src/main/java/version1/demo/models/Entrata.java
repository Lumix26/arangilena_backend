package version1.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Entrata extends Ordine{
    
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Acquirente aquirente;
}
