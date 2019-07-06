package com.guilherme.locadoraspringboot.controller;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.usuario.CriacaoUsuarioRequestDTO;
import com.guilherme.locadoraspringboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/criar", method = RequestMethod.POST)
    private DefaultResponseDTO criaUsuario(@Valid @RequestBody final CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO){
        return usuarioService.criarUsuario(criacaoUsuarioRequestDTO);
    }

}
