package version1.demo.models.prodotto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import version1.demo.models.ordine.Entrata;

@Entity
public class ProdottoEntrata extends Prodotto{
    
    @OneToMany(mappedBy = "prodottoEntrata")
    private List<Entrata> ordini;
    
    @Enumerated(EnumType.STRING)
    private Aziendali prodotto;


    public ProdottoEntrata() {
    }


    public Aziendali getProdotto() {
        return prodotto;
    }


    public void setProdotto(Aziendali prodotto) {
        this.prodotto = prodotto;
    }

    

    
}
