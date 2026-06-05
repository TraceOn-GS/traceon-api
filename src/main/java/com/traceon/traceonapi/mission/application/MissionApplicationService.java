package com.traceon.traceonapi.mission.application;

import com.traceon.traceonapi.mission.domain.entity.Missao;
import com.traceon.traceonapi.mission.domain.exception.MissaoNaoEncontradaException;
import com.traceon.traceonapi.mission.domain.repository.MissionRepositoryInterface;
import com.traceon.traceonapi.mission.dto.CreateMissionRequest;
import com.traceon.traceonapi.mission.dto.MissionResponse;
import com.traceon.traceonapi.mission.dto.MissionSummaryResponse;
import com.traceon.traceonapi.mission.dto.UpdateMissionRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MissionApplicationService {

    private final MissionRepositoryInterface repository;

    public MissionApplicationService(MissionRepositoryInterface repository) {
        this.repository = repository;
    }

    public MissionResponse create(CreateMissionRequest request) {
        Missao missao = new Missao(
                UUID.randomUUID(),
                request.codigo(),
                request.nome(),
                request.objetivo(),
                request.descricao(),
                request.prioridade(),
                request.dataFimPrevista()
        );

        return toResponse(repository.save(missao));
    }

    public List<MissionSummaryResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toSummaryResponse)
                .toList();
    }

    public MissionResponse findById(UUID id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new MissaoNaoEncontradaException(id));
    }

    public MissionResponse update(UUID id, UpdateMissionRequest request) {
        Missao missao = repository.findById(id)
                .orElseThrow(() -> new MissaoNaoEncontradaException(id));

        missao.atualizarDados(
                request.codigo(),
                request.nome(),
                request.objetivo(),
                request.descricao(),
                request.prioridade(),
                request.dataFimPrevista()
        );

        repository.update(missao);
        return toResponse(missao);
    }

    public void delete(UUID id) {
        repository.delete(id);
    }

    public MissionResponse iniciar(UUID id) {
        Missao missao = repository.findById(id)
                .orElseThrow(() -> new MissaoNaoEncontradaException(id));

        missao.iniciar();
        repository.update(missao);
        return toResponse(missao);
    }

    public MissionResponse pausar(UUID id) {
        Missao missao = repository.findById(id)
                .orElseThrow(() -> new MissaoNaoEncontradaException(id));

        missao.pausar();
        repository.update(missao);
        return toResponse(missao);
    }

    public MissionResponse finalizar(UUID id) {
        Missao missao = repository.findById(id)
                .orElseThrow(() -> new MissaoNaoEncontradaException(id));

        missao.finalizar();
        repository.update(missao);
        return toResponse(missao);
    }

    public MissionResponse cancelar(UUID id) {
        Missao missao = repository.findById(id)
                .orElseThrow(() -> new MissaoNaoEncontradaException(id));

        missao.cancelar();
        repository.update(missao);
        return toResponse(missao);
    }

    private MissionResponse toResponse(Missao missao) {
        return new MissionResponse(
                missao.getId(),
                missao.getCodigo(),
                missao.getNome(),
                missao.getObjetivo(),
                missao.getDescricao(),
                missao.getPrioridade(),
                missao.getStatus(),
                missao.getDataInicio(),
                missao.getDataFimPrevista(),
                missao.getDataEncerramento(),
                missao.getCriadoEm()
        );
    }

    private MissionSummaryResponse toSummaryResponse(Missao missao) {
        return new MissionSummaryResponse(
                missao.getId(),
                missao.getCodigo(),
                missao.getNome(),
                missao.getPrioridade(),
                missao.getStatus(),
                missao.getDataInicio(),
                missao.getDataFimPrevista()
        );
    }
}

