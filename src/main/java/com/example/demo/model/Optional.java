package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal; // Import corretto

@Entity
@Table(name = "OPTIONAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Optional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOptional")
    private Integer idOptional;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "Descrizione", columnDefinition = "TEXT")
    private String descrizione;

    @Column(name = "Prezzo", nullable = false, precision = 8, scale = 2)
    private BigDecimal prezzo;
}