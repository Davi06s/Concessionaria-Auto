package com.example.demo.repository;

import com.example.demo.model.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionalRepository extends JpaRepository<Optional, Integer> {
    java.util.Optional<Optional> findByNome(String nome);
}