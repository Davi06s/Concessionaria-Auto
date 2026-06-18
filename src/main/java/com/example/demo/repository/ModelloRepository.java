package com.example.demo.repository;

import com.example.demo.model.Modello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelloRepository extends JpaRepository<Modello, Integer> {
    java.util.Optional<Modello> findByNomeModello(String nomeModello);
}