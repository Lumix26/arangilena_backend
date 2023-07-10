package version1.demo.models.utente;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import version1.demo.models.ordine.Entrata;

@Entity
public class Acquirente extends Utente{

    public Acquirente() {
    }

    @Column(nullable = false, unique = true)
    private String ragioneSociale;

    @Column(nullable = false, unique = true)
    private String piva;


    @OneToOne
    @JoinColumn(name = "recapito")
    private Recapito recapito;



    @OneToMany(mappedBy = "acquirente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Entrata> ordini;

    
    public List<Entrata> getOrdini(){
        return ordini;
    }

    /*Getter & Setter */


    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getPiva() {
        return piva;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public Recapito getRecapito() {
        return recapito;
    }

    public void setRecapito(Recapito recapito) {
        this.recapito = recapito;
    }

    public void setOrdini(List<Entrata> ordini) {
        this.ordini = ordini;
    }



    
}
