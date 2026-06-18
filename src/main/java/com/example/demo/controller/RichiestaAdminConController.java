package com.example.demo.controller;

import com.example.demo.model.RichiestaAcquisto;
import com.example.demo.service.ConcessionariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/richieste")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") 
public class RichiestaAdminConController {
    private final ConcessionariaService service;

    @GetMapping ("/tutte")
    public ResponseEntity<List<RichiestaAcquisto>> getTutte() {
        return ResponseEntity.ok(service.getTutteLeRichieste());
    }

    @GetMapping
    public ResponseEntity<List<RichiestaAcquisto>> getInAttesa(@RequestParam(defaultValue = "In attesa") String stato) {
        if ("In attesa".equals(stato)) {
            return ResponseEntity.ok(service.getRichiesteInAttesa());
        }
        return ResponseEntity.ok(service.getTutteLeRichieste());
    }

    @PostMapping("/{id}/approva")
    public ResponseEntity<RichiestaAcquisto> approva(
        @PathVariable Integer id,
        @RequestParam(defaultValue = "") String note) {
            return ResponseEntity.ok(service.approvaRichiesta(id, note));
        }

    @PostMapping("/{id}/rifiuta")
    public ResponseEntity<RichiestaAcquisto> rifiuta(
        @PathVariable Integer id,
        @RequestParam(defaultValue = "") String note) {
            return ResponseEntity.ok(service.rifiutaRichiesta(id, note));
        }
}

    