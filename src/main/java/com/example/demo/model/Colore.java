package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal; // Import tassativo obbligatorio

@Entity
@Table(name = "COLORE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Colore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDColore")
    private Integer idColore;

    @Column(name = "NomeColore", nullable = false, length = 30)
    private String nomeColore;

    @Column(name = "Sovrapprezzo", precision = 8, scale = 2)
    private BigDecimal sovrapprezzo = BigDecimal.ZERO; 
}