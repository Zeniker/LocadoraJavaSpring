package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {

}

