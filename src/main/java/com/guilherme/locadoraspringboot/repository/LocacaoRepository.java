package com.guilherme.locadoraspringboot.repository;

import com.guilherme.locadoraspringboot.model.Copia;
import com.guilherme.locadoraspringboot.model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

    Optional<Locacao> findByCopiaAndAndDataDevolucaoIsNull(Copia copia);

}
