package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.usuario.LoginRequestDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    private MockHttpServletRequest httpServletRequest;

    @Before
    public void setUp() {
        httpServletRequest = new MockHttpServletRequest();
    }

    @Test
    public void loginSenhaIncorreta() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("gui.lls@hotmail.com");
        loginRequestDTO.setSenha("senha_errada");

        DefaultResponseDTO responseDTO = loginService.login(loginRequestDTO, httpServletRequest);
        assertEquals("ERRO", responseDTO.getStatus());
        assertEquals("Usuário ou senha incorretos", responseDTO.getMensagemErro());
    }

    @Test
    public void loginUsuarioIncorreto() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();;
        loginRequestDTO.setEmail("guil.lls@hotmail.com");
        loginRequestDTO.setSenha("123321");

        DefaultResponseDTO responseDTO = loginService.login(loginRequestDTO, httpServletRequest);
        assertEquals("ERRO", responseDTO.getStatus());
        assertEquals("Usuário ou senha incorretos", responseDTO.getMensagemErro());
    }

    @Test
    public void loginCorreto(){
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();;
        loginRequestDTO.setEmail("gui.lls@hotmail.com");
        loginRequestDTO.setSenha("123321");

        DefaultResponseDTO responseDTO = loginService.login(loginRequestDTO, httpServletRequest);
        assertEquals("OK", responseDTO.getStatus());
        assertNull(responseDTO.getMensagemErro());
    }

    @Test
    public void logoutSemLogin(){
        DefaultResponseDTO responseDTO = loginService.logout(httpServletRequest);
        assertEquals("ERRO", responseDTO.getStatus());
        assertEquals("Usuário não está autenticado, não é possível completar a ação.", responseDTO.getMensagemErro());
    }

    @Test
    public void logoutCorreto() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("gui.lls@hotmail.com");
        loginRequestDTO.setSenha("123321");
        loginService.login(loginRequestDTO, httpServletRequest);

        DefaultResponseDTO responseDTO = loginService.logout(httpServletRequest);
        assertEquals("OK", responseDTO.getStatus());
        assertNull(responseDTO.getMensagemErro());
    }
}