package version1.demo.models.ordine;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import version1.demo.models.utente.Acquirente;

@Entity
public class Entrata extends Ordine{
    
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Acquirente acquirente;
}
