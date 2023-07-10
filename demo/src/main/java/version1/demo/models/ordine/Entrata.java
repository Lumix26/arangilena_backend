package version1.demo.models.ordine;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import version1.demo.models.prodotto.ProdottoEntrata;
import version1.demo.models.utente.Acquirente;

@Entity
public class Entrata extends Ordine{

    @JoinColumn(name = "quantita", nullable = false)
    private int qnt;
    
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "tipo_prodotto")
    private Tipologia tipologia;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Acquirente acquirente;

    @ManyToOne
    @JoinColumn(name = "id_prodotto")
    private ProdottoEntrata prodottoEntrata;
}
