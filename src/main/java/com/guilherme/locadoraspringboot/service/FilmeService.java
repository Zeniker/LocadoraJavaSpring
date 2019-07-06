package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.dto.DefaultResponseDTO;
import com.guilherme.locadoraspringboot.dto.filme.*;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.model.Locacao;
import com.guilherme.locadoraspringboot.repository.CopiaRepository;
import com.guilherme.locadoraspringboot.repository.FilmeRepository;
import com.guilherme.locadoraspringboot.repository.LocacaoRepository;
import com.guilherme.locadoraspringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService extends DefaultService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private CopiaRepository copiaRepository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public FilmesDisponiveisResponseDTO listarFilmesDisponiveis(){
        FilmesDisponiveisResponseDTO responseDTO = new FilmesDisponiveisResponseDTO();

        try{
            responseDTO.setFilmeDisponiveis(filmeRepository.listarDisponiveis());
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
                throw new Exception("Filme não encontrado");
            }

            Filme filme = filmeOptional.get();

            List<Copia> copias = copiaRepository.findCopiaByFilme(filme);

            Copia copiaLocavel = null;

            boolean disponivel = false;
            for (Copia copia: copias) {
                Optional<Locacao> locacaoOptional = locacaoRepository.findByCopiaAndAndDataDevolucaoIsNull(copia);
                if(!locacaoOptional.isPresent()){
                    disponivel = true;
                    copiaLocavel = copia;
                    break;
                }
            }

            if(!disponivel){
                throw new Exception("Nenhuma cópia deste filme está disponível");
            }

            Locacao locacao = new Locacao();
            locacao.setCopia(copiaLocavel);
            locacao.setDataLocacao(LocalDateTime.now());

            //TODO PEGAR DO USUARIO LOGADO
            locacao.setLocador(usuarioRepository.findById(1).get());

            locacaoRepository.save(locacao);

            responseDTO.setIdCopiaLocada(copiaLocavel.getId());
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
                throw new Exception("Cópia de filme não encontrada");
            }

            Optional<Locacao> locacaoOptional = locacaoRepository.findByCopiaAndAndDataDevolucaoIsNull(copiaOptional.get());

            if(!locacaoOptional.isPresent()){
                throw new Exception("Esta cópia não está alugada, não é possível realizar devolução");
            }

            Locacao locacao = locacaoOptional.get();
            locacao.setDataDevolucao(LocalDateTime.now());

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
