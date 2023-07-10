package version1.demo.models.utente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Dipendente extends Utente{
    
    @Column(nullable = false, length = 15)
    private String nome;

    @Column(nullable = false, length = 15)
    private String cognome;

    @Column(nullable = false, length = 10, unique = true)
    private String telefono;

    @Column(nullable = false, length = 6)
    private double stipendio;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public Dipendente() {
    }

    /* Getter & Setter */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    

    
}
