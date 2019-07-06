package com.guilherme.locadoraspringboot.dto.filme;

import com.guilherme.locadoraspringboot.model.Diretor;

public class FilmesDisponiveisResponseDTO {
    private Integer id;
    private String titulo;
    private String diretor;
    private Integer quantidadeDisponivel;

    public FilmesDisponiveisResponseDTO(Integer id, String titulo, String diretor, Long quantidadeDisponivel) {
        this.id = id;
        this.titulo = titulo;
        this.diretor = diretor;
        this.quantidadeDisponivel = quantidadeDisponivel.intValue();
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

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

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
}
