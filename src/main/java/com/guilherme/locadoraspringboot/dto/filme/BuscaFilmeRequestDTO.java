package com.guilherme.locadoraspringboot.dto.filme;

import javax.validation.constraints.NotNull;

public class BuscaFilmeRequestDTO {

    @NotNull
    private String nomeFilme;

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }
}
