package com.guilherme.locadoraspringboot.exception;

public class DevolucacaoDeCopiaNaoAlugadaException extends Exception {

    public DevolucacaoDeCopiaNaoAlugadaException(){
        super("Esta cópia não está alugada, não é possível realizar devolução");
    }
}
