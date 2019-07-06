package com.guilherme.locadoraspringboot.dto.filme;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;

import java.util.List;

public class FilmesDisponiveisResponseDTO extends DefaultResponseDTO {
    private List<FilmeDisponivelDTO> filmeDisponiveis;

    public List<FilmeDisponivelDTO> getFilmeDisponiveis() {
        return filmeDisponiveis;
    }

    public void setFilmeDisponiveis(List<FilmeDisponivelDTO> filmeDisponiveis) {
        this.filmeDisponiveis = filmeDisponiveis;
    }
}
