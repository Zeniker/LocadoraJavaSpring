package com.guilherme.locadoraspringboot.dto.filme;

import javax.validation.constraints.NotNull;

public class DevolverFilmeRequestDTO {

    @NotNull
    private Integer idCopia;

    public Integer getIdCopia() {
        return idCopia;
    }

    public void setIdCopia(Integer idCopia) {
        this.idCopia = idCopia;
    }
}
