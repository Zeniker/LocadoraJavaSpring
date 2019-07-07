package com.guilherme.locadoraspringboot.utils;

import com.guilherme.locadoraspringboot.dto.usuario.LoginRequestDTO;
import com.guilherme.locadoraspringboot.service.LoginService;
import org.springframework.mock.web.MockHttpServletRequest;

public class LoginUtils {

    public static void realizaLoginPadrao(LoginService loginService){
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("gui.lls@hotmail.com");
        loginRequestDTO.setSenha("123321");
        loginService.login(loginRequestDTO, new MockHttpServletRequest());
    }
}
