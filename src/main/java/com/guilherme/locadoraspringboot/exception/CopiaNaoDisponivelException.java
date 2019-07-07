package com.guilherme.locadoraspringboot.exception;

public class CopiaNaoDisponivelException extends Exception {

    public CopiaNaoDisponivelException(){
        super("Nenhuma cópia deste filme está disponível");
    }
}
