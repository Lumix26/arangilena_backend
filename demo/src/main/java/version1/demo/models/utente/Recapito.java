package version1.demo.models.utente;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Recapito {

    @Override
    public String toString() {
        return "Recapito [id=" + id + ", mail=" + mail + ", fax=" + fax + ", telefono=" + telefono + ", acquirente="
                + acquirente + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recapito_generator")
    @SequenceGenerator(name = "recapito_generator", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 40)
    private String mail;
    @Column(nullable = false, length = 12)
    private String fax;
    @Column(nullable = false, length = 10)
    private String telefono;

    @OneToOne(mappedBy = "recapito")
    @JoinColumn(name = "acquirente")
    private Acquirente acquirente;

    public Recapito(String mail, String fax, String telefono) {
        this.mail = mail;
        this.fax = fax;
        this.telefono = telefono;
    }

    public Recapito() {
    }
    
    
    /* Getter & Setter */
    

    public String getMail() {
        return mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Acquirente getAcquirente() {
        return acquirente;
    }

    public void setAcquirente(Acquirente acquirente) {
        this.acquirente = acquirente;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    

    
    
}
