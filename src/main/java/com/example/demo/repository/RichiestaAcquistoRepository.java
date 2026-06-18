package com.example.demo.repository;

import com.example.demo.model.RichiestaAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RichiestaAcquistoRepository extends JpaRepository<RichiestaAcquisto, Integer> {
    
    List<RichiestaAcquisto> findByStato(String stato);
    List<RichiestaAcquisto> findByClienteIdCliente(Integer idCliente);
    List<RichiestaAcquisto> findByAziendaPartitaIva(String partitaIva);
}