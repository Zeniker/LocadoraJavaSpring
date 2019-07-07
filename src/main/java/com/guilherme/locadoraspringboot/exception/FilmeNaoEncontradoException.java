package com.guilherme.locadoraspringboot.exception;

public class FilmeNaoEncontradoException extends Exception {

    public FilmeNaoEncontradoException() {
        super("Filme não encontrado");
    }
}
