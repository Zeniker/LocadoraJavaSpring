package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.dto.usuario.LoginRequestDTO;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Diretor;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.model.Locacao;
import com.guilherme.locadoraspringboot.service.LocacaoService;
import com.guilherme.locadoraspringboot.service.LoginService;
import com.guilherme.locadoraspringboot.utils.CopiaUtils;
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
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LocacaoRepositoryTest {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private CopiaRepository copiaRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private LocacaoService locacaoService;

    @Autowired
    private LoginService loginService;

    @Before
    public void setUp(){
        LoginUtils.realizaLoginPadrao(loginService);
    }

    @Test
    public void findByCopiaAndAndDataDevolucaoIsNull() throws Exception {
        Copia copia = CopiaUtils.criarNovaCopia(diretorRepository, filmeRepository, copiaRepository);
        locacaoService.criarLocacao(copia);
        Optional<Locacao> locacao = locacaoRepository.findByCopiaAndAndDataDevolucaoIsNull(copia);
        assertTrue(locacao.isPresent());
        locacaoService.realizarDevolucao(copia);
        locacao = locacaoRepository.findByCopiaAndAndDataDevolucaoIsNull(copia);
        assertFalse(locacao.isPresent());
    }
}