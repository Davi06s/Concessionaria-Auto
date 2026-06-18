package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AZIENDA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Azienda {

    @Id
    @Column(name = "PartitaIVA", length = 11, columnDefinition = "char(11)")
    private String partitaIva;

    @Column(name = "RagioneSociale", nullable = false, length = 100)
    private String ragioneSociale; 

    @Column(name = "Indirizzo", length = 100)
    private String indirizzo;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @Column(name = "DataRegistrazione", insertable = false, updatable = false)
    private LocalDateTime dataRegistrazione;
}