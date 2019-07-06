package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import org.springframework.stereotype.Service;

@Service
public abstract class DefaultService {

    private final String STATUS_OK = "OK";
    private final String STATUS_ERRO = "ERRO";

    protected void marcaErroNoRetorno(DefaultResponseDTO defaultResponseDTO, String mensagemErro){
        defaultResponseDTO.setStatus(STATUS_ERRO);
        defaultResponseDTO.setMensagemErro(mensagemErro);
    }

    protected void marcaRetornoOK(DefaultResponseDTO defaultResponseDTO){
        defaultResponseDTO.setStatus(STATUS_OK);
    }

}
