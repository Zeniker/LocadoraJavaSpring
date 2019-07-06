package com.guilherme.locadoraspringboot.dto.filme;

import javax.validation.constraints.NotNull;

public class AlugarFilmeRequestDTO {

    @NotNull
    private Integer idFilme;

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }
}
