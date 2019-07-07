package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.usuario.CriacaoUsuarioRequestDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void criarUsuarioJaExistente() {
        CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO = new CriacaoUsuarioRequestDTO();
        criacaoUsuarioRequestDTO.setEmail("gui.lls@hotmail.com");
        criacaoUsuarioRequestDTO.setNome("Teste");
        criacaoUsuarioRequestDTO.setSenha("123321");

        DefaultResponseDTO responseDTO = usuarioService.criarUsuario(criacaoUsuarioRequestDTO);

        assertEquals("ERRO", responseDTO.getStatus());
        assertEquals("Email já cadastrado no sistema", responseDTO.getMensagemErro());
    }


    @Test
    public void criarUsuarioCorreto() {
        CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO = new CriacaoUsuarioRequestDTO();
        criacaoUsuarioRequestDTO.setEmail("teste@email.com");
        criacaoUsuarioRequestDTO.setNome("Teste");
        criacaoUsuarioRequestDTO.setSenha("123321");

        DefaultResponseDTO responseDTO = usuarioService.criarUsuario(criacaoUsuarioRequestDTO);

        assertEquals("OK", responseDTO.getStatus());
        assertNull(responseDTO.getMensagemErro());
    }
}