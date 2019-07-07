package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.filme.*;
import com.guilherme.locadoraspringboot.exception.CopiaNaoEncontradaException;
import com.guilherme.locadoraspringboot.exception.FilmeNaoEncontradoException;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.repository.CopiaRepository;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService extends DefaultService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private CopiaRepository copiaRepository;

    @Autowired
    private CopiaService copiaService;

    @Autowired
    private LocacaoService locacaoService;

    @Transactional
    public FilmesDisponiveisResponseDTO listarFilmesDisponiveis(){
        FilmesDisponiveisResponseDTO responseDTO = new FilmesDisponiveisResponseDTO();

        try{
            responseDTO.setFilmesDisponiveis(filmeRepository.listarDisponiveis());
            marcaRetornoOK(responseDTO);
        }catch (Exception e){
            marcaErroNoRetorno(responseDTO, e.getMessage());
        }
        return responseDTO;
    }

    @Transactional
    public AlugarFilmeResponseDTO alugarFilme(AlugarFilmeRequestDTO alugarFilmeRequestDTO){
        AlugarFilmeResponseDTO responseDTO = new AlugarFilmeResponseDTO();

        try{
            Optional<Filme> filmeOptional = filmeRepository.findById(alugarFilmeRequestDTO.getIdFilme());

            if(!filmeOptional.isPresent()){
                throw new FilmeNaoEncontradoException();
            }

            Filme filme = filmeOptional.get();
            Copia copia = copiaService.buscarCopiaDisponivel(filme);
            locacaoService.criarLocacao(copia);

            responseDTO.setIdCopiaLocada(copia.getId());
            marcaRetornoOK(responseDTO);
        }catch (Exception e){
            marcaErroNoRetorno(responseDTO, e.getMessage());
        }

        return responseDTO;
    }

    @Transactional
    public DefaultResponseDTO devolverFilme(DevolverFilmeRequestDTO devolverFilmeRequestDTO){
        DefaultResponseDTO responseDTO = new DefaultResponseDTO();

        try{
            Optional<Copia> copiaOptional = copiaRepository.findById(devolverFilmeRequestDTO.getIdCopia());

            if(!copiaOptional.isPresent()){
                throw new CopiaNaoEncontradaException();
            }

            locacaoService.realizarDevolucao(copiaOptional.get());

            marcaRetornoOK(responseDTO);
        }catch (Exception e){
            marcaErroNoRetorno(responseDTO, e.getMessage());
        }

        return responseDTO;
    }

    @Transactional
    public BuscaFilmeResponseDTO buscarFilme(BuscaFilmeRequestDTO buscaFilmeRequestDTO){
        BuscaFilmeResponseDTO responseDTO = new BuscaFilmeResponseDTO();

        try{
            List<Filme> filmes = filmeRepository.findByTituloIgnoreCaseIsLike(
                    "%" + buscaFilmeRequestDTO.getNomeFilme() + "%"
            );
            responseDTO.setFilmesEncontrados(filmes);

            marcaRetornoOK(responseDTO);
        }catch (Exception e){
            marcaErroNoRetorno(responseDTO, e.getMessage());
        }

        return responseDTO;
    }

}
