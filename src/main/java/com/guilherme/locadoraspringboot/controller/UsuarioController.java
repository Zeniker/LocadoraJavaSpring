package com.guilherme.locadoraspringboot.controller;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.usuario.CriacaoUsuarioRequestDTO;
import com.guilherme.locadoraspringboot.dto.usuario.LoginRequestDTO;
import com.guilherme.locadoraspringboot.service.LoginService;
import com.guilherme.locadoraspringboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/criar", method = RequestMethod.POST)
    public DefaultResponseDTO criarUsuario(@Valid @RequestBody final CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO){
        return usuarioService.criarUsuario(criacaoUsuarioRequestDTO);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DefaultResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest httpServletRequest){
        return loginService.login(loginRequestDTO, httpServletRequest);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public DefaultResponseDTO logout(HttpServletRequest httpServletRequest){
        return loginService.logout(httpServletRequest);
    }

}
