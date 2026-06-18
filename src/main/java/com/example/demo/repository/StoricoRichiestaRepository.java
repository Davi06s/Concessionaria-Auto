package com.example.demo.repository;

import com.example.demo.model.StoricoRichiesta;
import com.example.demo.model.RichiestaAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoricoRichiestaRepository extends JpaRepository<StoricoRichiesta, Integer> {
    List<StoricoRichiesta> findByRichiestaAcquistoOrderByDataModificaDesc(RichiestaAcquisto richiestaAcquisto);
}