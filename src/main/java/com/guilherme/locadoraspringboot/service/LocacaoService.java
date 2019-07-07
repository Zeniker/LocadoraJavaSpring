package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.exception.CopiaNaoDisponivelException;
import com.guilherme.locadoraspringboot.exception.DevolucacaoDeCopiaNaoAlugadaException;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Locacao;
import com.guilherme.locadoraspringboot.repository.LocacaoRepository;
import com.guilherme.locadoraspringboot.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LocacaoService extends DefaultService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    public Locacao criarLocacao(Copia copia) throws CopiaNaoDisponivelException {
        Optional<Locacao> locacaoOptional = locacaoRepository.findByCopiaAndAndDataDevolucaoIsNull(copia);

        if(locacaoOptional.isPresent()){
            throw new CopiaNaoDisponivelException();
        }

        Locacao locacao = new Locacao();
        locacao.setCopia(copia);
        locacao.setDataLocacao(LocalDateTime.now());
        locacao.setLocador(SessionUtils.getUsuarioLogado());

        locacaoRepository.save(locacao);

        return locacao;
    }

    public Locacao realizarDevolucao(Copia copia) throws DevolucacaoDeCopiaNaoAlugadaException {
        Optional<Locacao> locacaoOptional = locacaoRepository.findByCopiaAndAndDataDevolucaoIsNull(copia);

        if(!locacaoOptional.isPresent()){
            throw new DevolucacaoDeCopiaNaoAlugadaException();
        }

        Locacao locacao = locacaoOptional.get();
        locacao.setDataDevolucao(LocalDateTime.now());

        locacaoRepository.save(locacao);

        return locacao;
    }
}
