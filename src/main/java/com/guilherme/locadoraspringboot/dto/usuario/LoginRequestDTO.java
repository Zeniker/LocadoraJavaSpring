package com.guilherme.locadoraspringboot.dto.usuario;

import javax.validation.constraints.NotNull;

public class LoginRequestDTO {
    @NotNull
    private String email;

    @NotNull
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
