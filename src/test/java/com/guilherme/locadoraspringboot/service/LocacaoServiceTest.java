package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.usuario.LoginRequestDTO;
import com.guilherme.locadoraspringboot.exception.CopiaNaoDisponivelException;
import com.guilherme.locadoraspringboot.exception.DevolucacaoDeCopiaNaoAlugadaException;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.model.Locacao;
import com.guilherme.locadoraspringboot.repository.CopiaRepository;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;
import com.guilherme.locadoraspringboot.repository.LocacaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LocacaoServiceTest {

    @Autowired
    private LocacaoService locacaoService;

    @Autowired
    private CopiaRepository copiaRepository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private LoginService loginService;

    @Before
    public void setUp(){
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("gui.lls@hotmail.com");
        loginRequestDTO.setSenha("123321");
        loginService.login(loginRequestDTO, new MockHttpServletRequest());
    }

    @Test
    public void criarLocacaoCorreto() throws Exception{
        Copia copia = criarNovaCopia();

        Locacao locacao = locacaoService.criarLocacao(copia);
        Optional<Locacao> locacaoOptional = locacaoRepository.findById(locacao.getId());

        assertNotNull(locacao);
        assertTrue(locacaoOptional.isPresent());
        assertEquals(copia.getId(), locacaoOptional.get().getCopia().getId());
    }

    @Test(expected = CopiaNaoDisponivelException.class)
    public void criarLocacaoCorretoCopiaNaoDisponivel() throws Exception{
        Copia copia = criarNovaCopia();

        locacaoService.criarLocacao(copia);
        locacaoService.criarLocacao(copia);
    }

    @Test
    public void realizarDevolucaoCorreto() throws Exception {
        Copia copia = criarNovaCopia();

        locacaoService.criarLocacao(copia);
        Locacao locacao = locacaoService.realizarDevolucao(copia);
        assertNotNull(locacao.getDataDevolucao());
    }

    @Test(expected = DevolucacaoDeCopiaNaoAlugadaException.class)
    public void realizarDevolucaoCopiaNaoAlugada() throws Exception{
        Copia copia = criarNovaCopia();
        Locacao locacao = locacaoService.realizarDevolucao(copia);
        assertNotNull(locacao.getDataDevolucao());
    }

    @Transactional
    protected Copia criarNovaCopia(){
        Optional<Filme> filme = filmeRepository.findById(1);
        Copia copia = new Copia();
        copia.setFilme(filme.get());
        copiaRepository.save(copia);
        return copia;
    }
}