package com.guilherme.locadoraspringboot.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CriacaoUsuarioRequestDTO {

    @NotEmpty(message = "Favor informar o nome")
    private String nome;

    @NotEmpty(message = "Favor informar o email")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Favor informar a senha")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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
