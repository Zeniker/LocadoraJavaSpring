package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.usuario.LoginRequestDTO;
import com.guilherme.locadoraspringboot.exception.UsuarioNaoAutenticado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class LoginService extends DefaultService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public DefaultResponseDTO login(LoginRequestDTO loginRequestDTO, HttpServletRequest httpServletRequest){
        DefaultResponseDTO defaultResponseDTO = new DefaultResponseDTO();

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                    loginRequestDTO.getSenha());

            Authentication auth = authenticationManager.authenticate(token);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);

            marcaRetornoOK(defaultResponseDTO);
        } catch (BadCredentialsException e){
            marcaErroNoRetorno(defaultResponseDTO, "Usuário ou senha incorretos");
        }catch (Exception e){
            marcaErroNoRetorno(defaultResponseDTO, e.getMessage());
        }

        return defaultResponseDTO;
    }

    public DefaultResponseDTO logout(HttpServletRequest httpServletRequest) {
        DefaultResponseDTO defaultResponseDTO = new DefaultResponseDTO();

        try{
            SecurityContext securityContext = SecurityContextHolder.getContext();

            if(securityContext.getAuthentication() == null){
                throw new UsuarioNaoAutenticado();
            }
            securityContext.getAuthentication().setAuthenticated(false);

            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);

            marcaRetornoOK(defaultResponseDTO);
        }catch (Exception e){
            marcaErroNoRetorno(defaultResponseDTO, e.getMessage());
        }

        return defaultResponseDTO;
    }

}
