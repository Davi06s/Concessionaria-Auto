package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime; 

@Entity
@Table(name = "CLIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCliente")
    private Integer idCliente;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "Cognome", nullable = false, length = 50)
    private String cognome;

    @Column(name = "CodiceFiscale", nullable = false, unique = true, length = 16)
    private String codiceFiscale;

    @Column(name = "Indirizzo", length = 50)
    private String indirizzo;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @Column(name = "DataRegistrazione", insertable = false, updatable = false)
    private LocalDateTime dataRegistrazione;
}