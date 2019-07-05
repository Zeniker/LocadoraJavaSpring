package com.guilherme.locadoraspringboot.controller;

import com.guilherme.locadoraspringboot.dto.CriacaoUsuarioDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @RequestMapping(value = "/criar", method = RequestMethod.POST)
    private void criaUsuario(@Valid @RequestBody final CriacaoUsuarioDTO criacaoUsuarioDTO){

    }

}
