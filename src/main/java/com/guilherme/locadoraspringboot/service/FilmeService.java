package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.filme.FilmesDisponiveisResponseDTO;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FilmeService extends DefaultService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Transactional
    public List<FilmesDisponiveisResponseDTO> listarFilmesDisponiveis(){
        return filmeRepository.listarDisponiveis();
    }

}
