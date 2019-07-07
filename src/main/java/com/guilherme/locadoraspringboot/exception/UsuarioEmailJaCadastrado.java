package com.guilherme.locadoraspringboot.exception;

public class UsuarioEmailJaCadastrado extends Exception {

    public UsuarioEmailJaCadastrado() {
        super("Email já cadastrado no sistema");
    }
}
