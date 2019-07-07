package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.model.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void getUsuarioByEmail() {
        Optional<Usuario> usuario = usuarioRepository.findById(1);
        Usuario usuario2 = usuarioRepository.getUsuarioByEmail(usuario.get().getEmail());
        assertEquals(usuario.get().getId(), usuario2.getId());
    }
}