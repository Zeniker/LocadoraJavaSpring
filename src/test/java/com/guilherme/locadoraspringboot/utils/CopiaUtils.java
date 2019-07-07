package com.guilherme.locadoraspringboot.utils;

import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.repository.CopiaRepository;
import com.guilherme.locadoraspringboot.repository.DiretorRepository;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;

import javax.transaction.Transactional;

public class CopiaUtils {

    @Transactional
    public static Copia criarNovaCopia(DiretorRepository diretorRepository, FilmeRepository filmeRepository,
                                       CopiaRepository copiaRepository, Filme filme){
        if(filme == null){
            filme = FilmeUtils.criarFilme(diretorRepository, filmeRepository);
        }

        Copia copia = new Copia();
        copia.setFilme(filme);
        copiaRepository.save(copia);
        return copia;
    }

    @Transactional
    public static Copia criarNovaCopia(DiretorRepository diretorRepository, FilmeRepository filmeRepository,
                                       CopiaRepository copiaRepository){
        return CopiaUtils.criarNovaCopia(diretorRepository, filmeRepository, copiaRepository, null);
    }
}
