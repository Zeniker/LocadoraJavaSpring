package com.guilherme.locadoraspringboot.exception;

public class CopiaNaoEncontradaException extends Exception {

    public CopiaNaoEncontradaException(){
        super("Cópia de filme não encontrada");
    }
}
