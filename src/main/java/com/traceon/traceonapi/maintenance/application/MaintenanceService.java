package com.traceon.traceonapi.maintenance.application;

import com.traceon.traceonapi.device.domain.exception.DispositivoNaoEncontradoException;
import com.traceon.traceonapi.device.domain.repository.DispositivoRepositoryInterface;
import com.traceon.traceonapi.maintenance.domain.entity.ChecklistManutencao;
import com.traceon.traceonapi.maintenance.domain.entity.Manutencao;
import com.traceon.traceonapi.maintenance.domain.exception.ManutencaoNaoEncontradaException;
import com.traceon.traceonapi.maintenance.domain.exception.OcorrenciaNaoAssociadaException;
import com.traceon.traceonapi.maintenance.domain.repository.MaintenanceRepositoryInterface;
import com.traceon.traceonapi.maintenance.dto.*;
import com.traceon.traceonapi.occurrence.domain.exception.OcorrenciaNaoEncontradaException;
import com.traceon.traceonapi.occurrence.domain.repository.OccurrenceRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MaintenanceService {

    private final MaintenanceRepositoryInterface repository;

    private final DispositivoRepositoryInterface dispositivoRepository;

    private final OccurrenceRepositoryInterface occurrenceRepository;

    public MaintenanceService(
            MaintenanceRepositoryInterface repository,
            DispositivoRepositoryInterface dispositivoRepository,
            OccurrenceRepositoryInterface occurrenceRepository
    ) {

        this.repository = repository;
        this.dispositivoRepository = dispositivoRepository;
        this.occurrenceRepository = occurrenceRepository;

    }

    public MaintenanceResponse create(
            CreateMaintenanceRequest request
    ) {

        dispositivoRepository.buscarPorId(
                request.dispositivoId()
        ).orElseThrow(() ->
                new DispositivoNaoEncontradoException(
                        request.dispositivoId()
                )
        );

        if (request.ocorrenciaId() != null) {

            occurrenceRepository.buscarPorId(
                    request.ocorrenciaId()
            ).orElseThrow(() ->
                    new OcorrenciaNaoEncontradaException(
                            request.ocorrenciaId()
                    )
            );

        }

        Manutencao manutencao =
                new Manutencao(
                        UUID.randomUUID(),
                        request.codigo(),
                        request.titulo(),
                        request.descricao(),
                        request.dispositivoId(),
                        request.ocorrenciaId(),
                        request.tipo(),
                        request.responsavel()
                );

        repository.salvar(
                manutencao
        );

        return toResponse(
                manutencao
        );

    }

    public List<MaintenanceResponse> findAll() {

        return repository.buscarTodas()
                .stream()
                .map(this::toResponse)
                .toList();

    }

    public MaintenanceResponse findById(
            UUID id
    ) {

        return repository.buscarPorId(id)
                .map(this::toResponse)
                .orElseThrow(() ->
                        new ManutencaoNaoEncontradaException(id)
                );

    }

    public void delete(
            UUID id
    ) {

        repository.remover(id);

    }

    public MaintenanceResponse iniciar(
            UUID id
    ) {

        Manutencao manutencao =
                buscarManutencao(id);

        manutencao.iniciar();

        repository.atualizar(
                manutencao
        );

        return toResponse(
                manutencao
        );

    }

    public MaintenanceResponse aguardarPeca(
            UUID id
    ) {

        Manutencao manutencao =
                buscarManutencao(id);

        manutencao.aguardarPeca();

        repository.atualizar(
                manutencao
        );

        return toResponse(
                manutencao
        );

    }

    public MaintenanceResponse concluir(
            UUID id
    ) {

        Manutencao manutencao =
                buscarManutencao(id);

        manutencao.concluir();

        repository.atualizar(
                manutencao
        );

        return toResponse(
                manutencao
        );

    }

    public MaintenanceResponse cancelar(
            UUID id
    ) {

        Manutencao manutencao =
                buscarManutencao(id);

        manutencao.cancelar();

        repository.atualizar(
                manutencao
        );

        return toResponse(
                manutencao
        );

    }

    public MaintenanceResponse adicionarChecklist(
            UUID manutencaoId,
            CreateChecklistRequest request
    ) {

        Manutencao manutencao =
                buscarManutencao(
                        manutencaoId
                );

        manutencao.adicionarChecklist(
                request.descricao()
        );

        repository.atualizar(
                manutencao
        );

        return toResponse(
                manutencao
        );

    }

    public MaintenanceResponse concluirChecklist(
            UUID manutencaoId,
            UUID checklistId
    ) {

        Manutencao manutencao =
                buscarManutencao(
                        manutencaoId
                );

        manutencao.concluirChecklist(
                checklistId
        );

        repository.atualizar(
                manutencao
        );

        return toResponse(
                manutencao
        );

    }

    private Manutencao buscarManutencao(
            UUID id
    ) {

        return repository.buscarPorId(id)
                .orElseThrow(() ->
                        new ManutencaoNaoEncontradaException(
                                id
                        )
                );

    }

    private MaintenanceResponse toResponse(
            Manutencao manutencao
    ) {

        return new MaintenanceResponse(
                manutencao.getId(),
                manutencao.getCodigo(),
                manutencao.getTitulo(),
                manutencao.getDescricao(),
                manutencao.getDispositivoId(),
                manutencao.getOcorrenciaId(),
                manutencao.getTipo(),
                manutencao.getStatus(),
                manutencao.getResponsavel(),
                manutencao.getAbertaEm(),
                manutencao.getIniciadaEm(),
                manutencao.getConcluidaEm(),
                manutencao.getCanceladaEm(),
                manutencao.getChecklists()
                        .stream()
                        .map(this::toChecklistResponse)
                        .toList()
        );

    }

    private ChecklistResponse toChecklistResponse(
            ChecklistManutencao checklist
    ) {

        return new ChecklistResponse(
                checklist.getId(),
                checklist.getDescricao(),
                checklist.isConcluido(),
                checklist.getConcluidoEm()
        );

    }

}