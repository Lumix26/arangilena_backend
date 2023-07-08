package version1.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Acquirente extends Utente{
    

    
    private List<Entrata> ordini;

    @OneToMany(mappedBy = "acquirente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Entrata> getOrdini(){
        return ordini;
    }
}
