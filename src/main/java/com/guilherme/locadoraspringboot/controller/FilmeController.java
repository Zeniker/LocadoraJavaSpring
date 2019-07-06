package com.guilherme.locadoraspringboot.controller;

import com.guilherme.locadoraspringboot.dto.filme.FilmesDisponiveisResponseDTO;
import com.guilherme.locadoraspringboot.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @RequestMapping(value = "/disponiveis")
    public List<FilmesDisponiveisResponseDTO> listaFilmesDisponiveis(){
        try{
            return filmeService.listarFilmesDisponiveis();
        }catch (Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }
}
