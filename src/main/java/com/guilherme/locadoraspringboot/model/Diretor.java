package com.guilherme.locadoraspringboot.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Diretor implements Serializable {

    @GenericGenerator(name="diretor_gen" , strategy="increment")
    @GeneratedValue(generator="diretor_gen")
    @Id
    private Integer id;

    @Size(max = 100)
    @NotNull
    private String nome;

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
}
