package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "MODELLO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDModello")
    private Integer idModello;

    @ManyToOne
    @JoinColumn(name = "IDMarca", nullable = false)
    private Marca marca;

    @Column(name = "NomeModello", nullable = false, length = 50)
    private String nomeModello;

    @Column(name = "Descrizione", nullable = false, length = 255)
    private String descrizione;

    @Column(name = "PrezzoBase", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoBase;
}