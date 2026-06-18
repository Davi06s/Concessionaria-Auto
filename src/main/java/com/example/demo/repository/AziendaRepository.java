package com.example.demo.repository;

import com.example.demo.model.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, String> {
    Optional<Azienda> findByEmail(String email);
}