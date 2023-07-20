package version1.demo.models.utente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "indirizzo_generator")
    @SequenceGenerator(name = "indirizzo_generator", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 15)
    private String citta;
    @Column(nullable = false, length = 5)
    private String cap;
    @Column(nullable = false)
    private String via;
    
    public String getCap() {
        return cap;
    }

    @Override
    public String toString() {
        return "Indirizzo [id=" + id + ", citta=" + citta + ", cap=" + cap + ", via=" + via + ", n_civico=" + n_civico
                + ", utente=" + utente + "]";
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Column(nullable = false, length = 3)
    private int n_civico;

    @OneToOne(mappedBy = "indirizzo")
    private Utente utente;

    
    public Indirizzo() {
    }
    
    public Indirizzo(String citta, String cap, String via, int n_civico) {
        this.citta = citta;
        this.cap = cap;
        this.via = via;
        this.n_civico = n_civico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }


    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getN_civico() {
        return n_civico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setN_civico(int n_civico) {
        this.n_civico = n_civico;
    }


    /*public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }*/


    
}
