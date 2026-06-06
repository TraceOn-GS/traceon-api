package com.traceon.traceonapi.maintenance.infrastructure.repository;

import com.traceon.traceonapi.maintenance.domain.entity.Manutencao;
import com.traceon.traceonapi.maintenance.domain.repository.MaintenanceRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MaintenanceRepository
        implements MaintenanceRepositoryInterface {

    private final Map<UUID, Manutencao> manutencoes =
            new LinkedHashMap<>();

    @Override
    public Optional<Manutencao> buscarPorId(
            UUID id
    ) {

        return Optional.ofNullable(
                manutencoes.get(id)
        );

    }

    @Override
    public List<Manutencao> buscarTodas() {

        return new ArrayList<>(
                manutencoes.values()
        );

    }

    @Override
    public List<Manutencao> buscarPorDispositivo(
            UUID dispositivoId
    ) {

        return manutencoes.values()
                .stream()
                .filter(m ->
                        m.getDispositivoId()
                                .equals(dispositivoId)
                )
                .toList();

    }

    @Override
    public List<Manutencao> buscarPorOcorrencia(
            UUID ocorrenciaId
    ) {

        return manutencoes.values()
                .stream()
                .filter(m ->
                        ocorrenciaId.equals(
                                m.getOcorrenciaId()
                        )
                )
                .toList();

    }

    @Override
    public Manutencao salvar(
            Manutencao manutencao
    ) {

        manutencoes.put(
                manutencao.getId(),
                manutencao
        );

        return manutencao;

    }

    @Override
    public Manutencao atualizar(
            Manutencao manutencao
    ) {

        manutencoes.put(
                manutencao.getId(),
                manutencao
        );

        return manutencao;

    }

    @Override
    public void remover(
            UUID id
    ) {

        manutencoes.remove(id);

    }

}