package com.traceon.traceonapi.occurrence.domain.repository;

import com.traceon.traceonapi.occurrence.domain.entity.Ocorrencia;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OccurrenceRepositoryInterface {

    Ocorrencia salvar(
            Ocorrencia ocorrencia
    );

    Optional<Ocorrencia> buscarPorId(
            UUID id
    );

    List<Ocorrencia> buscarTodas();

    Ocorrencia atualizar(
            Ocorrencia ocorrencia
    );

    void remover(
            UUID id
    );

}