package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Filme;
import jdk.nashorn.internal.runtime.options.Option;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CopiaRepositoryTest {

    @Autowired
    private CopiaRepository copiaRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Test
    public void findCopiaByFilme() {
        Optional<Filme> filme = filmeRepository.findById(1);

        List<Copia> copiaList = copiaRepository.findCopiaByFilme(filme.get());
        assertEquals(2, copiaList.size());
    }
}