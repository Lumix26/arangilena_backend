package version1.demo.models.prodotto;




import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import version1.demo.models.ordine.Entrata;

@Entity
public class ProdottoEntrata extends Prodotto{
    
    
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaE categoriaE;


    public ProdottoEntrata() {
    }


    /* Getter & Setter */



    public CategoriaE getCategoriaE() {
        return categoriaE;
    }


    public void setCategoriaE(CategoriaE categoriaE) {
        this.categoriaE = categoriaE;
    }

    

    
}
