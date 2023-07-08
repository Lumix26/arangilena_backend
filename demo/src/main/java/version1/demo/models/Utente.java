package version1.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Utente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_generator")
    @SequenceGenerator(name = "utente_generator", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 10)
    private String username;

    @Column(nullable = false, length = 20)
    private String password;
}
