package com.example.demo.controller;

import com.example.demo.dto.RichiestaRequestDTO;
import com.example.demo.model.RichiestaAcquisto;
import com.example.demo.service.ConcessionariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/concessionaria")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") 
public class ConcessionariaController {

    private final ConcessionariaService service;

    //LATO CLIENTE: Sottomissione e finalizzazione della richiesta d'acquisto.
    @PostMapping("/richieste")
    public ResponseEntity<RichiestaAcquisto> inviaRichiesta(@RequestBody RichiestaRequestDTO dto) {
        RichiestaAcquisto nuovaPratica = service.creaRichiestaAcquisto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovaPratica);
    }

    // LATO CLIENTE: Recupero richieste per cliente privato
    @GetMapping("/richieste/cliente/{idCliente}")
    public ResponseEntity<List<RichiestaAcquisto>> getRichiesteByCliente(@PathVariable Integer idCliente) {
        List<RichiestaAcquisto> richieste = service.getRichiesteByCliente(idCliente);
        return ResponseEntity.ok(richieste);
    }

    // LATO CLIENTE: Recupero richieste per azienda
    @GetMapping("/richieste/azienda/{partitaIva}")
    public ResponseEntity<List<RichiestaAcquisto>> getRichiesteByAzienda(@PathVariable String partitaIva) {
        List<RichiestaAcquisto> richieste = service.getRichiesteByAzienda(partitaIva);
        return ResponseEntity.ok(richieste);
    }

    // LATO CONCESSIONARIA: Recupero dell'elenco delle richieste in stato "In attesa".
    @GetMapping("/operatore/in-attesa")
    public ResponseEntity<List<RichiestaAcquisto>> getRichiesteInAttesa() {
        List<RichiestaAcquisto> inAttesa = service.getRichiesteInAttesa();
        return ResponseEntity.ok(inAttesa);
    }

    // LATO CONCESSIONARIA: Azione di approvazione o rifiuto di una pratica con note.
    @PutMapping("/operatore/richieste/{id}/stato")
    public ResponseEntity<RichiestaAcquisto> aggiornaStatoRichiesta(
            @PathVariable Integer id,
            @RequestParam String nuovoStato,
            @RequestParam Integer idOperatore,
            @RequestBody String note) {
        
        RichiestaAcquisto praticaAggiornata = service.cambiaStatoPratica(id, nuovoStato, idOperatore, note);
        return ResponseEntity.ok(praticaAggiornata);
    }
}