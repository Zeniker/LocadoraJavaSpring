package com.guilherme.locadoraspringboot.exception;

public class UsuarioNaoAutenticado extends Exception {

    public UsuarioNaoAutenticado() {
        super("Usuário não está autenticado, não é possível completar a ação.");
    }
}
