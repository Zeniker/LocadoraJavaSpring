package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.model.Copia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopiaRepository extends JpaRepository<Copia, Integer> {
}
