package com.guilherme.locadoraspringboot.dto.filme;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;

public class AlugarFilmeResponseDTO extends DefaultResponseDTO {
    private Integer idCopiaLocada;

    public Integer getIdCopiaLocada() {
        return idCopiaLocada;
    }

    public void setIdCopiaLocada(Integer idCopiaLocada) {
        this.idCopiaLocada = idCopiaLocada;
    }
}
