package com.guilherme.locadoraspringboot.dto.filme;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;

import java.util.List;

public class FilmesDisponiveisResponseDTO extends DefaultResponseDTO {
    private List<FilmeDisponivelDTO> filmesDisponiveis;

    public List<FilmeDisponivelDTO> getFilmesDisponiveis() {
        return filmesDisponiveis;
    }

    public void setFilmesDisponiveis(List<FilmeDisponivelDTO> filmesDisponiveis) {
        this.filmesDisponiveis = filmesDisponiveis;
    }
}
