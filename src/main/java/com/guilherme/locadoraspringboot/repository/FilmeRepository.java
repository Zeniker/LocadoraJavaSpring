package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

}
