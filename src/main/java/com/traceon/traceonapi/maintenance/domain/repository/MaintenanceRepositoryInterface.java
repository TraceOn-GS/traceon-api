package com.traceon.traceonapi.maintenance.domain.repository;

import com.traceon.traceonapi.maintenance.domain.entity.Manutencao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaintenanceRepositoryInterface {

    Optional<Manutencao> buscarPorId(
            UUID id
    );

    List<Manutencao> buscarTodas();

    List<Manutencao> buscarPorDispositivo(
            UUID dispositivoId
    );

    List<Manutencao> buscarPorOcorrencia(
            UUID ocorrenciaId
    );

    Manutencao salvar(
            Manutencao manutencao
    );

    Manutencao atualizar(
            Manutencao manutencao
    );

    void remover(
            UUID id
    );

}