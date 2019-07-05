package com.guilherme.locadoraspringboot.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

    @GenericGenerator(name="usuario_gen" , strategy="increment")
    @GeneratedValue(generator="usuario_gen")
    @Id
    private Integer id;

    @Size(max = 100)
    @NotNull
    private String nome;

    @Size(max = 150)
    @NotNull
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
