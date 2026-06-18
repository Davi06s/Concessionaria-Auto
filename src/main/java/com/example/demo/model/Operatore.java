package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OPERATORE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operatore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOperatore")
    private Integer idOperatore;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "Cognome", nullable = false, length = 50)
    private String cognome;

    @Column(name = "Username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "Ruolo", nullable = false, length = 30)
    private String ruolo;   
}