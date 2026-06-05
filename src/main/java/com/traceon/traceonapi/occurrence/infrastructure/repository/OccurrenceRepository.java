package com.traceon.traceonapi.occurrence.infrastructure.repository;

import com.traceon.traceonapi.occurrence.domain.entity.Ocorrencia;
import com.traceon.traceonapi.occurrence.domain.repository.OccurrenceRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OccurrenceRepository
        implements OccurrenceRepositoryInterface {

    private final Map<UUID, Ocorrencia> ocorrencias =
            new LinkedHashMap<>();

    @Override
    public Ocorrencia salvar(
            Ocorrencia ocorrencia
    ) {

        ocorrencias.put(
                ocorrencia.getId(),
                ocorrencia
        );

        return ocorrencia;

    }

    @Override
    public Optional<Ocorrencia> buscarPorId(
            UUID id
    ) {

        return Optional.ofNullable(
                ocorrencias.get(id)
        );

    }

    @Override
    public List<Ocorrencia> buscarTodas() {

        return new ArrayList<>(
                ocorrencias.values()
        );

    }

    @Override
    public Ocorrencia atualizar(
            Ocorrencia ocorrencia
    ) {

        ocorrencias.put(
                ocorrencia.getId(),
                ocorrencia
        );

        return ocorrencia;

    }

    @Override
    public void remover(
            UUID id
    ) {

        ocorrencias.remove(id);

    }

}