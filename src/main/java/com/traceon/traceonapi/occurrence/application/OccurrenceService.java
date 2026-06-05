package com.traceon.traceonapi.occurrence.application;

import com.traceon.traceonapi.alert.domain.entity.Alerta;
import com.traceon.traceonapi.alert.domain.exception.AlertaNaoEncontradoException;
import com.traceon.traceonapi.alert.domain.repository.AlertaRepositoryInterface;
import com.traceon.traceonapi.device.domain.exception.DispositivoNaoEncontradoException;
import com.traceon.traceonapi.device.domain.repository.DispositivoRepositoryInterface;
import com.traceon.traceonapi.occurrence.domain.entity.Ocorrencia;
import com.traceon.traceonapi.occurrence.domain.exception.OcorrenciaNaoEncontradaException;
import com.traceon.traceonapi.occurrence.domain.repository.OccurrenceRepositoryInterface;
import com.traceon.traceonapi.occurrence.dto.CreateOccurrenceRequest;
import com.traceon.traceonapi.occurrence.dto.OccurrenceResponse;
import com.traceon.traceonapi.occurrence.dto.UpdateOccurrenceRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OccurrenceService {

    private final OccurrenceRepositoryInterface repository;

    private final AlertaRepositoryInterface alertRepository;

    private final DispositivoRepositoryInterface dispositivoRepository;

    public OccurrenceService(
            OccurrenceRepositoryInterface repository,
            AlertaRepositoryInterface alertRepository,
            DispositivoRepositoryInterface dispositivoRepository
    ) {

        this.repository = repository;
        this.alertRepository = alertRepository;
        this.dispositivoRepository = dispositivoRepository;

    }

    public OccurrenceResponse create(
            CreateOccurrenceRequest request
    ) {

        Alerta alerta =
                alertRepository.buscarPorId(
                        request.alertaId()
                ).orElseThrow(() ->
                        new AlertaNaoEncontradoException(
                                request.alertaId()
                        )
                );

        dispositivoRepository.buscarPorId(
                        alerta.getDispositivoId()
                )
                .orElseThrow(() ->
                        new DispositivoNaoEncontradoException(
                                alerta.getDispositivoId()
                        )
                );

        Ocorrencia ocorrencia =
                new Ocorrencia(
                        UUID.randomUUID(),
                        request.codigo(),
                        request.titulo(),
                        request.descricao(),
                        alerta.getId(),
                        alerta.getDispositivoId(),
                        request.responsavel()
                );

        repository.salvar(
                ocorrencia
        );

        return toResponse(
                ocorrencia
        );

    }

    public List<OccurrenceResponse> findAll() {

        return repository.buscarTodas()
                .stream()
                .map(this::toResponse)
                .toList();

    }

    public OccurrenceResponse findById(
            UUID id
    ) {

        return repository.buscarPorId(id)
                .map(this::toResponse)
                .orElseThrow(() ->
                        new OcorrenciaNaoEncontradaException(
                                id
                        )
                );

    }

    public OccurrenceResponse update(
            UUID id,
            UpdateOccurrenceRequest request
    ) {

        Ocorrencia ocorrencia =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new OcorrenciaNaoEncontradaException(
                                        id
                                )
                        );

        ocorrencia.atualizarDescricao(
                request.descricao()
        );

        ocorrencia.atualizarResponsavel(
                request.responsavel()
        );

        repository.atualizar(
                ocorrencia
        );

        return toResponse(
                ocorrencia
        );

    }

    public void delete(
            UUID id
    ) {

        repository.remover(id);

    }

    public OccurrenceResponse iniciarAnalise(
            UUID id
    ) {

        Ocorrencia ocorrencia =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new OcorrenciaNaoEncontradaException(
                                        id
                                )
                        );

        ocorrencia.iniciarAnalise();

        repository.atualizar(
                ocorrencia
        );

        return toResponse(
                ocorrencia
        );

    }

    public OccurrenceResponse iniciarTratamento(
            UUID id
    ) {

        Ocorrencia ocorrencia =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new OcorrenciaNaoEncontradaException(
                                        id
                                )
                        );

        ocorrencia.iniciarTratamento();

        repository.atualizar(
                ocorrencia
        );

        return toResponse(
                ocorrencia
        );

    }

    public OccurrenceResponse encerrar(
            UUID id
    ) {

        Ocorrencia ocorrencia =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new OcorrenciaNaoEncontradaException(
                                        id
                                )
                        );

        ocorrencia.encerrar();

        repository.atualizar(
                ocorrencia
        );

        return toResponse(
                ocorrencia
        );

    }

    public OccurrenceResponse cancelar(
            UUID id
    ) {

        Ocorrencia ocorrencia =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new OcorrenciaNaoEncontradaException(
                                        id
                                )
                        );

        ocorrencia.cancelar();

        repository.atualizar(
                ocorrencia
        );

        return toResponse(
                ocorrencia
        );

    }

    private OccurrenceResponse toResponse(
            Ocorrencia ocorrencia
    ) {
        Alerta alerta = alertRepository.buscarPorId(
                ocorrencia.getAlertaId()
        ).orElseThrow(() ->
                new AlertaNaoEncontradoException(
                        ocorrencia.getAlertaId()
                )
        );
        return new OccurrenceResponse(
                ocorrencia.getId(),
                ocorrencia.getCodigo(),
                ocorrencia.getTitulo(),
                ocorrencia.getDescricao(),
                ocorrencia.getAlertaId(),
                alerta.getSeveridade(),
                ocorrencia.getStatus(),
                ocorrencia.getAbertaEm(),
                ocorrencia.getEncerradaEm()
        );

    }

}