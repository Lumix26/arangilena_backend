package version1.demo.models.prodotto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Prodotto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prodotto_generator" )
    @SequenceGenerator(name = "prodotto_generator", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "prezzo", nullable = false)
    private double prezzoBase;
    @Column(name = "descrizione", nullable = true)
    private String descrizione;

    

    public Prodotto() {
    }



    /* Getter & Setter */


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public double getPrezzoBase() {
        return prezzoBase;
    }


    public void setPrezzoBase(double prezzoBase) {
        this.prezzoBase = prezzoBase;
    }


    public String getDescrizione() {
        return descrizione;
    }


    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    
    
}
