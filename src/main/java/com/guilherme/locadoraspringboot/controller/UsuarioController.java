package com.guilherme.locadoraspringboot.controller;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.usuario.CriacaoUsuarioRequestDTO;
import com.guilherme.locadoraspringboot.dto.usuario.LoginRequestDTO;
import com.guilherme.locadoraspringboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Resource
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/criar", method = RequestMethod.POST)
    private DefaultResponseDTO criarUsuario(@Valid @RequestBody final CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO){
        return usuarioService.criarUsuario(criacaoUsuarioRequestDTO);
    }

    @RequestMapping(value = "/login")
    public void login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest httpServletRequest){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                loginRequestDTO.getSenha());

        Authentication auth = authenticationManager.authenticate(token);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
    }

    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest httpServletRequest){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.getAuthentication().setAuthenticated(false);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
    }

}
