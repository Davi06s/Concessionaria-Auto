package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CONFIGURAZIONE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configurazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDConfigurazione")
    private Integer idConfigurazione;

    @ManyToOne
    @JoinColumn(name = "IDModello", nullable = false)
    private Modello modello;

    @ManyToOne
    @JoinColumn(name = "IDColore", nullable = false)
    private Colore colore;

    @Column(name = "DataCreazione", insertable = false, updatable = false)
    private LocalDateTime dataCreazione;

    @Column(name = "PrezzoTotale", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoTotale;

    @ManyToMany
    @JoinTable(
        name = "CONFIGURAZIONE_OPTIONAL",
        joinColumns = @JoinColumn(name = "IDConfigurazione"),
        inverseJoinColumns = @JoinColumn(name = "IDOptional")
    )
    private List<Optional> optional;
}