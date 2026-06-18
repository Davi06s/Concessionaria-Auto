package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MARCA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMarca")
    private Integer idMarca;

    @Column(name = "NomeMarca", nullable = false, length = 50)
    private String nomeMarca;
}