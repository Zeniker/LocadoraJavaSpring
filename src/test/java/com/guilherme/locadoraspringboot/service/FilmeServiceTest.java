package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.filme.*;
import com.guilherme.locadoraspringboot.dto.usuario.LoginRequestDTO;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Diretor;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.repository.CopiaRepository;
import com.guilherme.locadoraspringboot.repository.DiretorRepository;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;
import com.guilherme.locadoraspringboot.repository.LocacaoRepository;
import com.guilherme.locadoraspringboot.utils.FilmeUtils;
import com.guilherme.locadoraspringboot.utils.LoginUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FilmeServiceTest {

    @Autowired
    private CopiaRepository copiaRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private DiretorRepository diretorRepository;

    @Before
    public void setUp() throws Exception {
        LoginUtils.realizaLoginPadrao(loginService);
    }

    @Test
    public void listarFilmesDisponiveis() {
        FilmesDisponiveisResponseDTO responseDTO = filmeService.listarFilmesDisponiveis();
        assertEquals("OK", responseDTO.getStatus());
        assertNull(responseDTO.getMensagemErro());
        assertTrue(responseDTO.getFilmesDisponiveis().size() > 0);
    }

    @Test
    public void alugarFilme() {
        Filme filme = FilmeUtils.criarFilmeComCopia(diretorRepository, filmeRepository, copiaRepository);
        List<Copia> copia = copiaRepository.findCopiaByFilme(filme);

        AlugarFilmeRequestDTO requestDTO = new AlugarFilmeRequestDTO();
        requestDTO.setIdFilme(filme.getId());
        AlugarFilmeResponseDTO responseDTO = filmeService.alugarFilme(requestDTO);

        assertEquals("OK", responseDTO.getStatus());
        assertNull(responseDTO.getMensagemErro());
        assertEquals(copia.get(0).getId(), responseDTO.getIdCopiaLocada());
    }

    @Test
    public void alugarFilmeNaoDisponivel() {
        Filme filme = FilmeUtils.criarFilmeComCopia(diretorRepository, filmeRepository, copiaRepository);

        AlugarFilmeRequestDTO requestDTO = new AlugarFilmeRequestDTO();
        requestDTO.setIdFilme(filme.getId());
        filmeService.alugarFilme(requestDTO);
        AlugarFilmeResponseDTO responseDTO = filmeService.alugarFilme(requestDTO);

        assertEquals("ERRO", responseDTO.getStatus());
        assertEquals("Nenhuma cópia deste filme está disponível", responseDTO.getMensagemErro());
    }

    @Test
    public void devolverFilme() {
        Filme filme = FilmeUtils.criarFilmeComCopia(diretorRepository, filmeRepository, copiaRepository);
        List<Copia> copia = copiaRepository.findCopiaByFilme(filme);

        AlugarFilmeRequestDTO requestDTO = new AlugarFilmeRequestDTO();
        requestDTO.setIdFilme(filme.getId());
        filmeService.alugarFilme(requestDTO);

        DevolverFilmeRequestDTO devolverFilmeRequestDTO = new DevolverFilmeRequestDTO();
        devolverFilmeRequestDTO.setIdCopia(copia.get(0).getId());

        DefaultResponseDTO responseDTO = filmeService.devolverFilme(devolverFilmeRequestDTO);

        assertEquals("OK", responseDTO.getStatus());
        assertNull(responseDTO.getMensagemErro());
    }

    @Test
    public void devolverFilmeNaoAlugado() {
        Filme filme = FilmeUtils.criarFilmeComCopia(diretorRepository, filmeRepository, copiaRepository);
        List<Copia> copia = copiaRepository.findCopiaByFilme(filme);

        DevolverFilmeRequestDTO devolverFilmeRequestDTO = new DevolverFilmeRequestDTO();
        devolverFilmeRequestDTO.setIdCopia(copia.get(0).getId());

        DefaultResponseDTO responseDTO = filmeService.devolverFilme(devolverFilmeRequestDTO);

        assertEquals("ERRO", responseDTO.getStatus());
        assertEquals("Esta cópia não está alugada, não é possível realizar devolução", responseDTO.getMensagemErro());
    }

    @Test
    public void buscarFilme() {
        BuscaFilmeRequestDTO buscaFilmeRequestDTO = new BuscaFilmeRequestDTO();
        buscaFilmeRequestDTO.setNomeFilme("moan");

        BuscaFilmeResponseDTO responseDTO = filmeService.buscarFilme(buscaFilmeRequestDTO);
        assertEquals("OK", responseDTO.getStatus());
        assertNull(responseDTO.getMensagemErro());
        assertEquals(1, responseDTO.getFilmesEncontrados().size());
    }


}