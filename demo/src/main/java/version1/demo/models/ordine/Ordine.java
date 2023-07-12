package version1.demo.models.ordine;

import java.time.LocalDate;


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
public abstract class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordine_generator" )
    @SequenceGenerator(name = "ordine_generator", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 10)
    private LocalDate data;

    @Column(nullable = true, length = 512)
    private String descrizione;



    

    public Ordine() {
    }


    /* Getter & Setter */
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    
    
}
