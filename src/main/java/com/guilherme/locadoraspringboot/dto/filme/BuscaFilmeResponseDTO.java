package com.guilherme.locadoraspringboot.dto.filme;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.model.Filme;

import java.util.List;

public class BuscaFilmeResponseDTO extends DefaultResponseDTO {

    private List<Filme> filmesEncontrados;

    public List<Filme> getFilmesEncontrados() {
        return filmesEncontrados;
    }

    public void setFilmesEncontrados(List<Filme> filmesEncontrados) {
        this.filmesEncontrados = filmesEncontrados;
    }
}
