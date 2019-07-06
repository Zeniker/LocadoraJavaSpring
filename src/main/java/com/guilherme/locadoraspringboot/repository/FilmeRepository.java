package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.dto.filme.FilmesDisponiveisResponseDTO;
import com.guilherme.locadoraspringboot.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    @Query("SELECT new com.guilherme.locadoraspringboot.dto.filme.FilmesDisponiveisResponseDTO(f.id, f.titulo, d.nome, count(f)) " +
            "  FROM Filme f, Copia c, Diretor d" +
            " WHERE c.filme = f " +
            "   AND f.diretor = d" +
            "   AND NOT EXISTS ( SELECT l.copia " +
            "                      FROM Locacao l " +
            "                     WHERE l.copia = c " +
            "                       AND l.dataDevolucao IS NULL)" +
            " GROUP BY f.id, f.diretor, f.titulo")
    List<FilmesDisponiveisResponseDTO> listarDisponiveis();
}
