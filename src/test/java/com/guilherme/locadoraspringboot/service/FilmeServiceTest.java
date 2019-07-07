package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.filme.AlugarFilmeRequestDTO;
import com.guilherme.locadoraspringboot.dto.filme.AlugarFilmeResponseDTO;
import com.guilherme.locadoraspringboot.dto.filme.FilmesDisponiveisResponseDTO;
import com.guilherme.locadoraspringboot.dto.usuario.LoginRequestDTO;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Diretor;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.repository.CopiaRepository;
import com.guilherme.locadoraspringboot.repository.DiretorRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FilmeServiceTest {

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

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private DiretorRepository diretorRepository;

    @Before
    public void setUp() throws Exception {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("gui.lls@hotmail.com");
        loginRequestDTO.setSenha("123321");
        loginService.login(loginRequestDTO, new MockHttpServletRequest());
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
        Filme filme = criarFilme();
        List<Copia> copia = copiaRepository.findCopiaByFilme(filme);

        AlugarFilmeRequestDTO requestDTO = new AlugarFilmeRequestDTO();
        requestDTO.setIdFilme(filme.getId());
        AlugarFilmeResponseDTO responseDTO = filmeService.alugarFilme(requestDTO);

        assertEquals("OK", responseDTO.getStatus());
        assertNull(responseDTO.getMensagemErro());
        assertEquals(copia.get(0).getId(), responseDTO.getIdCopiaLocada());
    }

    @Test
    public void devolverFilme() {
    }

    @Test
    public void buscarFilme() {
    }

    @Transactional
    protected Filme criarFilme(){
        Optional<Diretor> diretor = diretorRepository.findById(1);

        Filme filme = new Filme();
        filme.setTitulo("Coração de dragão");
        filme.setDiretor(diretor.get());

        filmeRepository.save(filme);

        Copia copia = new Copia();
        copia.setFilme(filme);

        copiaRepository.save(copia);

        return filme;
    }


}