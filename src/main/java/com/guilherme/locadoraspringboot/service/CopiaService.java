package com.guilherme.locadoraspringboot.service;

import com.guilherme.locadoraspringboot.exception.CopiaNaoDisponivelException;
import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Filme;
import com.guilherme.locadoraspringboot.model.Locacao;
import com.guilherme.locadoraspringboot.repository.CopiaRepository;
import com.guilherme.locadoraspringboot.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CopiaService extends DefaultService {

    @Autowired
    private CopiaRepository copiaRepository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Transactional
    public Copia buscarCopiaDisponivel(Filme filme) throws CopiaNaoDisponivelException{
        List<Copia> copias = copiaRepository.findCopiaByFilme(filme);

        for (Copia copia: copias) {
            Optional<Locacao> locacaoOptional = locacaoRepository.findByCopiaAndAndDataDevolucaoIsNull(copia);
            if(!locacaoOptional.isPresent()){
                return copia;
            }
        }

        throw new CopiaNaoDisponivelException();

    }
}
