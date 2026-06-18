package com.example.demo.service;

import com.example.demo.dto.RichiestaRequestDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConcessionariaService {

    private final ModelloRepository modelloRepository;
    private final ColoreRepository coloreRepository;
    private final OptionalRepository optionalRepository;
    private final RichiestaAcquistoRepository richiestaAcquistoRepository;
    private final ClienteRepository clienteRepository;
    private final AziendaRepository aziendaRepository;
    private final OperatoreRepository operatoreRepository;
    private final StoricoRichiestaRepository storicoRichiestaRepository;
    private final EmailService emailService;

    public BigDecimal calcolaPrezzoTotale(RichiestaRequestDTO dto) {
        Modello modello = modelloRepository.findById(dto.getIdModello())
                .orElseThrow(() -> new RuntimeException("Modello non trovato con ID: " + dto.getIdModello()));

        Colore colore = coloreRepository.findById(dto.getIdColore())
                .orElseThrow(() -> new RuntimeException("Colore non trovato con ID: " + dto.getIdColore()));

        BigDecimal totale = modello.getPrezzoBase().add(colore.getSovrapprezzo());

        if (dto.getIdOptional() != null && !dto.getIdOptional().isEmpty()) {
            for (Integer idOpt : dto.getIdOptional()) {
                com.example.demo.model.Optional opt = optionalRepository.findById(idOpt)
                        .orElseThrow(() -> new RuntimeException("Optional non trovato con ID: " + idOpt));
                totale = totale.add(opt.getPrezzo());
            }
        }
        return totale;
    }

    @Transactional
    public RichiestaAcquisto creaRichiestaAcquisto(RichiestaRequestDTO dto) {
        if ((dto.getIdCliente() != null && dto.getPartitaIva() != null) ||
            (dto.getIdCliente() == null && dto.getPartitaIva() == null)) {
            throw new IllegalArgumentException("Errore: Specificare uno e un solo acquirente.");
        }

        BigDecimal prezzoFinale = calcolaPrezzoTotale(dto);
        log.info("Prezzo calcolato per richiesta: {} (dto: {})", prezzoFinale, dto);

        if (prezzoFinale == null) {
            log.warn("Prezzo finale risulta NULL, imposto a ZERO per evitare errore DB");
            prezzoFinale = BigDecimal.ZERO;
        }

        Modello modello = modelloRepository.findById(dto.getIdModello()).get();
        Colore colore = coloreRepository.findById(dto.getIdColore()).get();

        List<com.example.demo.model.Optional> optionalSelezionati = new ArrayList<>();
        if (dto.getIdOptional() != null) {
            optionalSelezionati = optionalRepository.findAllById(dto.getIdOptional());
        }

        Configurazione nuovaConfigurazione = new Configurazione();
        nuovaConfigurazione.setModello(modello);
        nuovaConfigurazione.setColore(colore);
        nuovaConfigurazione.setPrezzoTotale(prezzoFinale);
        nuovaConfigurazione.setOptional(optionalSelezionati);

        RichiestaAcquisto richiesta = new RichiestaAcquisto();
        richiesta.setConfigurazione(nuovaConfigurazione);

        if (dto.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente non trovato"));
            richiesta.setCliente(cliente);
            richiesta.setStato("Approvata");
        } else {
            Azienda azienda = aziendaRepository.findById(dto.getPartitaIva())
                    .orElseThrow(() -> new RuntimeException("Azienda non trovata"));
            richiesta.setAzienda(azienda);
            richiesta.setStato("In attesa");
        }

        return richiestaAcquistoRepository.save(richiesta);
    }

    public List<RichiestaAcquisto> getRichiesteInAttesa() {
        return richiestaAcquistoRepository.findByStato("In attesa");
    }

    @Transactional
    public RichiestaAcquisto cambiaStatoPratica(Integer idRichiesta, String nuovoStato, Integer idOperatore, String note) {
        if (note == null || note.trim().isEmpty()) {
            throw new IllegalArgumentException("Le note sono obbligatorie.");
        }

        RichiestaAcquisto richiesta = richiestaAcquistoRepository.findById(idRichiesta)
                .orElseThrow(() -> new RuntimeException("Richiesta non trovata"));

        Operatore operatore = operatoreRepository.findById(idOperatore)
                .orElseThrow(() -> new RuntimeException("Operatore non trovato"));

        String statoPrecedente = richiesta.getStato();
        richiesta.setStato(nuovoStato);
        RichiestaAcquisto salvata = richiestaAcquistoRepository.save(richiesta);

        StoricoRichiesta storico = new StoricoRichiesta();
        storico.setRichiestaAcquisto(salvata);
        storico.setOperatore(operatore);
        storico.setStatoPrecedente(statoPrecedente);
        storico.setStatoNuovo(nuovoStato);
        storico.setNote(note);
        storicoRichiestaRepository.save(storico);

        emailService.inviaEmailStato(salvata, note);

        return salvata;
    }

    @Transactional
    public RichiestaAcquisto approvaRichiesta(Integer idRichiesta, String note) {
        RichiestaAcquisto richiesta = richiestaAcquistoRepository.findById(idRichiesta)
                .orElseThrow(() -> new RuntimeException("Richiesta non trovata"));

        String statoPrecedente = richiesta.getStato();
        richiesta.setStato("Approvata");

        RichiestaAcquisto salvata = richiestaAcquistoRepository.save(richiesta);

        StoricoRichiesta storico = new StoricoRichiesta();
        storico.setRichiestaAcquisto(salvata); 
        storico.setDataModifica(java.time.LocalDateTime.now());
        storico.setStatoPrecedente(statoPrecedente);
        storico.setStatoNuovo("Approvata");
        storico.setNote(note);
        storicoRichiestaRepository.save(storico);

        emailService.inviaEmailStato(salvata, note);

        return salvata;
    }

    @Transactional
    public RichiestaAcquisto rifiutaRichiesta(Integer idRichiesta, String note) {
        if (note == null || note.trim().isEmpty()) {
            throw new IllegalArgumentException("La motivazione (nota) è obbligatoria per rifiutare la richiesta.");
        }

        RichiestaAcquisto richiesta = richiestaAcquistoRepository.findById(idRichiesta)
                .orElseThrow(() -> new RuntimeException("Richiesta non trovata"));

        String statoPrecedente = richiesta.getStato();
        richiesta.setStato("Rifiutata");

        RichiestaAcquisto salvata = richiestaAcquistoRepository.save(richiesta);

        StoricoRichiesta storico = new StoricoRichiesta();
        storico.setRichiestaAcquisto(salvata); 
        storico.setDataModifica(java.time.LocalDateTime.now());
        storico.setStatoPrecedente(statoPrecedente);
        storico.setStatoNuovo("Rifiutata");
        storico.setNote(note);
        storicoRichiestaRepository.save(storico);

        emailService.inviaEmailStato(salvata, note);

        return salvata;
    }

    public List<RichiestaAcquisto> getTutteLeRichieste() {
        return richiestaAcquistoRepository.findAll();
    }

    public List<RichiestaAcquisto> getRichiesteByCliente(Integer idCliente) {
        return richiestaAcquistoRepository.findByClienteIdCliente(idCliente);
    }

    public List<RichiestaAcquisto> getRichiesteByAzienda(String partitaIva) {
        return richiestaAcquistoRepository.findByAziendaPartitaIva(partitaIva);
    }
}

