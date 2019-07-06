package com.guilherme.locadoraspringboot.controller;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.filme.*;
import com.guilherme.locadoraspringboot.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @RequestMapping(value = "/disponiveis")
    public FilmesDisponiveisResponseDTO listaFilmesDisponiveis(){
        return filmeService.listarFilmesDisponiveis();
    }

    @RequestMapping(value = "/alugar")
    public AlugarFilmeResponseDTO alugarFilme(@Valid @RequestBody AlugarFilmeRequestDTO alugarFilmeRequestDTO){
        return filmeService.alugarFilme(alugarFilmeRequestDTO);
    }

    @RequestMapping(value = "/devolver")
    public DefaultResponseDTO devolverFilme(@Valid @RequestBody DevolverFilmeRequestDTO devolverFilmeRequestDTO){
        return filmeService.devolverFilme(devolverFilmeRequestDTO);
    }

    @RequestMapping(value = "/buscar")
    public BuscaFilmeResponseDTO buscarFilme(@Valid @RequestBody BuscaFilmeRequestDTO buscaFilmeRequestDTO){
        return filmeService.buscarFilme(buscaFilmeRequestDTO);
    }

}