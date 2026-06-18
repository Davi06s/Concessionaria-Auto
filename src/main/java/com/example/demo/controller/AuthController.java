package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.model.Cliente;
import com.example.demo.model.Azienda;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) {
        try {
            LoginResponseDTO response = authService.login(loginDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("errore", e.getMessage()));
        }
    }

    @PostMapping("/registrazione/cliente")
    public ResponseEntity<?> registraCliente(@RequestBody Cliente cliente) {
        try {
            Cliente nuovoCliente = authService.registraCliente(cliente);
            return ResponseEntity.ok(nuovoCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("errore", e.getMessage()));
        }
    }

    @PostMapping("/registrazione/azienda")
    public ResponseEntity<?> registraAzienda(@RequestBody Azienda azienda) {
        try {
            Azienda nuovaAzienda = authService.registraAzienda(azienda);
            return ResponseEntity.ok(nuovaAzienda);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("errore", e.getMessage()));
        }
    }
}
