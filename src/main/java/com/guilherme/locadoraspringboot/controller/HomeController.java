package com.guilherme.locadoraspringboot.controller;

import com.guilherme.locadoraspringboot.model.Diretor;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.repository.DiretorRepository;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class HomeController {

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @RequestMapping("/")
    @Transactional
    public String home(){
        Diretor diretor = new Diretor();
        diretor.setNome("Pauzudo");

        diretorRepository.save(diretor);

        Filme filme = new Filme();
        filme.setDiretor(diretor);
        filme.setTitulo("Pimbas");

        filmeRepository.save(filme);


        return diretor.getNome();
    }
}
