package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.model.RichiestaAcquisto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.repository.query.Param;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {    
    private final JavaMailSender mailSender;

    public void inviaEmailStato(RichiestaAcquisto richiesta, String note) {
        String emailDestinatario = null;
        String nomeDestinatario = null;

        if (richiesta.getCliente() != null) {
            emailDestinatario = richiesta.getCliente().getEmail();
            nomeDestinatario = richiesta.getCliente().getNome() + " " + richiesta.getCliente().getCognome();
        } else if (richiesta.getAzienda() != null) {
            emailDestinatario = richiesta.getAzienda().getEmail();
            nomeDestinatario = richiesta.getAzienda().getRagioneSociale();
        }
        
        if (emailDestinatario == null || emailDestinatario.trim().isEmpty()) {
            log.warn("Impossibile inviare l'email: nessun indirizzo email trovato per la richiesta ID {}",
                richiesta.getIdRichiesta());
            return;
        }

        String stato = richiesta.getStato();
        String subject = "Aggiornamento Stato Pratica #" + richiesta.getIdRichiesta();

        StringBuilder body = new StringBuilder();
        body.append("Gentile ").append(nomeDestinatario).append(",\n\n");
        body.append("Le comunichiamo che lo stato della sua pratica (richiesta di acquisto ID: ")
        .append(richiesta.getIdRichiesta())
        .append(") è stato aggiornato a: ")
        .append(stato.toUpperCase())
        .append(".\n\n");

        if (note != null && !note.trim().isEmpty()) {
            body.append("Dettagli e motivazioni fornite dall'operatore:\n")
            .append(note)
            .append("\n\n");
        }

        body.append("Cordiali saluti, \n");
        body.append("Lo staff di Gestione Concessionaria Auto");
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDestinatario);
            message.setSubject(subject);
            message.setText(body.toString());
            message.setFrom("no-reply@concessionaria.com");

            log.info("Tentativo di invio email a: {} per la richiesta ID {}", emailDestinatario, richiesta.getIdRichiesta());
            mailSender.send(message); 
            log.info("Email inviata corettamente a: {} per la richiesta ID {}", emailDestinatario, richiesta.getIdRichiesta());
        } catch (Exception e) {
            log.error("ATTENZIONE: Errore durante l'invio dell'email a {} per la richiesta ID {}: {}",
            emailDestinatario, richiesta.getIdRichiesta(), e.getMessage());
        }
    }
}