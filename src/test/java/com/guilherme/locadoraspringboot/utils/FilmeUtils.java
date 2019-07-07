package com.guilherme.locadoraspringboot.utils;

import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Diretor;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.repository.CopiaRepository;
import com.guilherme.locadoraspringboot.repository.DiretorRepository;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public class FilmeUtils {

    @Transactional
    public static Filme criarFilme(DiretorRepository diretorRepository, FilmeRepository filmeRepository){
        Optional<Diretor> diretor = diretorRepository.findById(1);

        Filme filme = new Filme();
        filme.setTitulo("Coração de dragão");
        filme.setDiretor(diretor.get());

        filmeRepository.save(filme);

        return filme;
    }

    @Transactional
    public static Filme criarFilmeComCopia(DiretorRepository diretorRepository, FilmeRepository filmeRepository,
                                           CopiaRepository copiaRepository){
        Filme filme = FilmeUtils.criarFilme(diretorRepository, filmeRepository);

        CopiaUtils.criarNovaCopia(diretorRepository, filmeRepository, copiaRepository, filme);

        return filme;
    }
}
