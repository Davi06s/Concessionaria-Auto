package com.example.demo.service;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.model.Cliente;
import com.example.demo.model.Azienda;
import com.example.demo.model.Operatore;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.AziendaRepository;
import com.example.demo.repository.OperatoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ClienteRepository clienteRepository;
    private final AziendaRepository aziendaRepository;
    private final OperatoreRepository operatoreRepository;

    public LoginResponseDTO login(LoginRequestDTO request) {
        String input = request.getEmailOrUsername();
        String password = request.getPassword();

        Optional<Operatore> operatoreOpt = 
        operatoreRepository.findByUsername(input);
        if (operatoreOpt.isEmpty()) {
            operatoreOpt = operatoreRepository.findByEmail(input);
        }
        if (operatoreOpt.isPresent()) {
            Operatore op = operatoreOpt.get();
            if (op.getPassword().equals(password)) {
                return new LoginResponseDTO("simulated-token", "OPERATORE", op);
            }
        }
        
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(input);
        if (clienteOpt.isPresent()) {
            Cliente cl = clienteOpt.get();
            if (cl.getPassword().equals(password)) {
                return new LoginResponseDTO("simulated-token", "CLIENTE", cl);
            }
        }

        Optional<Azienda> aziendaOpt = aziendaRepository.findByEmail(input);
        if (aziendaOpt.isPresent()) {
            Azienda az = aziendaOpt.get();
            if (az.getPassword().equals(password)) {
                return new LoginResponseDTO("simulated-token", "AZIENDA", az);
            }
        }
        throw new RuntimeException("Credenziali non valide.");
    }

    @Transactional
    public Cliente registraCliente(Cliente cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("Email già registrata per un altro cliente");
        }
        return clienteRepository.save(cliente);
    }

     @Transactional
    public Azienda registraAzienda(Azienda azienda) {
        if (aziendaRepository.findById(azienda.getPartitaIva()).isPresent()) {
            throw new RuntimeException("Partita IVA già registrata.");
        }
        if (aziendaRepository.findByEmail(azienda.getEmail()).isPresent()) {
            throw new RuntimeException("Email già registrata per un altra azienda.");
        }
        return aziendaRepository.save(azienda);
    }
}