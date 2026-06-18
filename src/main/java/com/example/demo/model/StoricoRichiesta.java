package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "STORICO_RICHIESTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoricoRichiesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDStorico")
    private Integer idStorico;

    @ManyToOne
    @JoinColumn(name = "IDRichiesta", nullable = false)
    private RichiestaAcquisto richiestaAcquisto;

    @ManyToOne
    @JoinColumn(name = "IDOperatore")
    private Operatore operatore;

    @Column(name = "DataModifica", insertable = false, updatable = false)
    private LocalDateTime dataModifica;

    @Column(name = "StatoPrecedente", length = 20)
    private String statoPrecedente;

    @Column(name = "StatoNuovo", nullable = false, length = 20)
    private String statoNuovo;

    @Column(name = "Note", columnDefinition = "TEXT")
    private String note;
}