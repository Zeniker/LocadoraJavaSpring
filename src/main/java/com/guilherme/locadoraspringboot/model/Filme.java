package com.guilherme.locadoraspringboot.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Filme {

    @GenericGenerator(name="filme_gen" , strategy="increment")
    @GeneratedValue(generator="filme_gen")
    @Id
    private Integer id;

    @Size(max = 100)
    @NotNull
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Diretor diretor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }
}
