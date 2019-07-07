package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.usuario.CriacaoUsuarioRequestDTO;
import com.guilherme.locadoraspringboot.model.Usuario;
import com.guilherme.locadoraspringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioService extends DefaultService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public DefaultResponseDTO criarUsuario(CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO){
        DefaultResponseDTO responseDTO = new DefaultResponseDTO();

        try{
            Usuario usuario = usuarioRepository.getUsuarioByEmail(criacaoUsuarioRequestDTO.getEmail());

            if(usuario != null){
                throw new Exception("Nome de usuário já existe");
            }

            usuario = new Usuario();
            usuario.setEmail(criacaoUsuarioRequestDTO.getEmail());
            usuario.setNome(criacaoUsuarioRequestDTO.getNome());
            usuario.setSenha(bCryptPasswordEncoder.encode(criacaoUsuarioRequestDTO.getSenha()));

            usuarioRepository.save(usuario);

            marcaRetornoOK(responseDTO);
        }catch (Exception e){
            marcaErroNoRetorno(responseDTO, e.getMessage());
        }

        return responseDTO;
    }

}
