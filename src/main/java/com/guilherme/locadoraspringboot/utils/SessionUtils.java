package com.guilherme.locadoraspringboot.utils;

import com.guilherme.locadoraspringboot.dto.impl.UserDetailsImpl;
import com.guilherme.locadoraspringboot.model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUtils {

    public static Usuario getUsuarioLogado(){
        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsuario();
    }
}
