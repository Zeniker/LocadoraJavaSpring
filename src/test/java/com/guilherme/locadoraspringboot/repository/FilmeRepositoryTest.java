package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.dto.filme.FilmeDisponivelDTO;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.model.Locacao;
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
public class FilmeRepositoryTest {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private CopiaRepository copiaRepository;

    @Test
    public void listarDisponiveis() {
        List<FilmeDisponivelDTO> filmeDisponivel = filmeRepository.listarDisponiveis();
        for (FilmeDisponivelDTO filme: filmeDisponivel) {
            Optional<Filme> filmeOptional = filmeRepository.findById(filme.getId());
            assertTrue(filmeOptional.isPresent());

            List<Copia> copias = copiaRepository.findCopiaByFilme(filmeOptional.get());
            Integer quantidadeDisponivel = 0;
            for (Copia copia: copias) {
                Optional<Locacao> locacaoOptional = locacaoRepository.findByCopiaAndAndDataDevolucaoIsNull(copia);
                if(!locacaoOptional.isPresent()){
                    quantidadeDisponivel++;
                }
            }

            assertEquals(filme.getQuantidadeDisponivel(), quantidadeDisponivel);
        }
    }

    @Test
    public void findByTituloIgnoreCaseIsLike() {
        List<Filme> filmes = filmeRepository.findByTituloIgnoreCaseIsLike("%moan%");
        assertEquals(1, filmes.size());
    }
}