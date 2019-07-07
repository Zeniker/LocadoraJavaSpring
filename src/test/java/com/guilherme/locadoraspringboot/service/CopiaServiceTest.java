package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.exception.CopiaNaoDisponivelException;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CopiaServiceTest {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private CopiaService copiaService;

    @Test
    public void buscarCopiaDisponivelCorreto() {
        Optional<Filme> filmeOptional = filmeRepository.findById(1);

        try{
            Copia copia = copiaService.buscarCopiaDisponivel(filmeOptional.get());
            assertEquals((Integer) 1, copia.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(expected = CopiaNaoDisponivelException.class)
    public void buscarCopiaDisponivelCopiaNaoDisponivel() throws Exception {
        Optional<Filme> filmeOptional = filmeRepository.findById(5);
        copiaService.buscarCopiaDisponivel(filmeOptional.get());
    }
}