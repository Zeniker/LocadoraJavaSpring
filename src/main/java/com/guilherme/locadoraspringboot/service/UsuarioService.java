package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.usuario.CriacaoUsuarioRequestDTO;
import com.guilherme.locadoraspringboot.model.Usuario;
import com.guilherme.locadoraspringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioService extends DefaultService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public DefaultResponseDTO criarUsuario(CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO){
        DefaultResponseDTO responseDTO = new DefaultResponseDTO();

        try{
            Usuario usuario = new Usuario();
            usuario.setEmail(criacaoUsuarioRequestDTO.getEmail());
            usuario.setNome(criacaoUsuarioRequestDTO.getEmail());
            usuario.setSenha(criacaoUsuarioRequestDTO.getSenha());

            usuarioRepository.save(usuario);

            marcaRetornoOK(responseDTO);
        }catch (Exception e){
            marcaErroNoRetorno(responseDTO, e.getMessage());
        }

        return responseDTO;
    }

}
