package com.guilherme.locadoraspringboot.controller;

import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Diretor;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.repository.CopiaRepository;
import com.guilherme.locadoraspringboot.repository.DiretorRepository;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private CopiaRepository copiaRepository;

    @RequestMapping("/")
    @Transactional
    public String home(){
        Diretor diretor = new Diretor();
        diretor.setNome("Steve");

        diretorRepository.save(diretor);

        Filme filme = new Filme();
        filme.setDiretor(diretor);
        filme.setTitulo("O fim da lua");

        filmeRepository.save(filme);

        Copia copia = new Copia();
        copia.setFilme(filme);

        copiaRepository.save(copia);

        copia = new Copia();
        copia.setFilme(filme);

        copiaRepository.save(copia);

        return diretor.getNome();
    }

    @RequestMapping("/d")
    @Transactional
    public Diretor hom(){
        Optional<Diretor> diretor = diretorRepository.findById(1);

        return diretor.isPresent() ? diretor.get() : null;
    }
}
