package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RICHIESTA_ACQUISTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RichiestaAcquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDRichiesta")
    private Integer idRichiesta;

    // ABILITA IL SALVATAGGIO A CASCATA DELLA CONFIGURAZIONE
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "IDConfigurazione", nullable = false, unique = true)
    private Configurazione configurazione;

    @ManyToOne
    @JoinColumn(name = "IDCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "PartitaIVA")
    private Azienda azienda;

    @Column(name = "DataRichiesta", insertable = false, updatable = false)
    private LocalDateTime dataRichiesta;

    @Column(name = "Stato", nullable = false)
    private String stato = "In attesa"; 
}