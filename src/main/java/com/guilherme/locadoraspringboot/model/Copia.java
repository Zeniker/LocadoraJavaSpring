package com.guilherme.locadoraspringboot.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Copia {

    @GenericGenerator(name="copia_gen" , strategy="increment")
    @GeneratedValue(generator="copia_gen")
    @Id
    private Integer id;

    @ManyToOne
    @NotNull
    private Filme filme;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}
