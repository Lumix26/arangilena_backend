package version1.demo.models.ordine;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordine_generator" )
    @SequenceGenerator(name = "ordine_generator", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 10)
    private LocalDate data;

    @Column(nullable = true, length = 512)
    private String descrizione;
}
