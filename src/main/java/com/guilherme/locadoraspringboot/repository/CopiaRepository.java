package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CopiaRepository extends JpaRepository<Copia, Integer> {

    List<Copia> findCopiaByFilme(Filme filme);
}
