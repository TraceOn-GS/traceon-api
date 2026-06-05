package com.traceon.traceonapi.alert.application;

import com.traceon.traceonapi.alert.domain.entity.Alerta;
import com.traceon.traceonapi.alert.domain.enums.StatusAlerta;
import com.traceon.traceonapi.alert.domain.exception.AlertaNaoEncontradoException;

import com.traceon.traceonapi.alert.domain.repository.AlertaRepositoryInterface;
import com.traceon.traceonapi.alert.dto.AlertResponse;
import com.traceon.traceonapi.alert.dto.CreateAlertRequest;
import com.traceon.traceonapi.alert.dto.UpdateSeverityRequest;
import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;
import com.traceon.traceonapi.device.domain.exception.DispositivoNaoEncontradoException;
import com.traceon.traceonapi.device.domain.repository.DispositivoRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlertService {

    private final AlertaRepositoryInterface repository;

    private final DispositivoRepositoryInterface dispositivoRepository;

    public AlertService(
            AlertaRepositoryInterface repository,
            DispositivoRepositoryInterface dispositivoRepository
    ) {

        this.repository = repository;
        this.dispositivoRepository = dispositivoRepository;

    }

    public AlertResponse create(
            CreateAlertRequest request
    ) {

        DispositivoEspacial dispositivo =
                dispositivoRepository.buscarPorId(
                                request.dispositivoId()
                        )
                        .orElseThrow(() ->
                                new DispositivoNaoEncontradoException(
                                        request.dispositivoId()
                                )
                        );

        Alerta alerta =
                new Alerta(
                        UUID.randomUUID(),
                        gerarCodigo(),
                        dispositivo.getId(),
                        request.telemetriaId(),
                        request.tipo(),
                        request.severidade(),
                        request.descricao(),
                        request.origem()
                );

        repository.salvar(alerta);

        return toResponse(alerta);

    }

    public List<AlertResponse> findAll() {

        return repository.buscarTodos()
                .stream()
                .map(this::toResponse)
                .toList();

    }

    public AlertResponse findById(
            UUID id
    ) {

        return repository.buscarPorId(id)
                .map(this::toResponse)
                .orElseThrow(() ->
                        new AlertaNaoEncontradoException(id)
                );

    }

    public List<AlertResponse> findByDevice(
            UUID dispositivoId
    ) {

        dispositivoRepository.buscarPorId(dispositivoId)
                .orElseThrow(() ->
                        new DispositivoNaoEncontradoException(
                                dispositivoId
                        )
                );

        return repository.buscarPorDispositivo(dispositivoId)
                .stream()
                .map(this::toResponse)
                .toList();

    }

    public AlertResponse iniciarAnalise(
            UUID alertaId
    ) {

        Alerta alerta =
                buscarEntidade(alertaId);

        alerta.iniciarAnalise();

        repository.salvar(alerta);

        return toResponse(alerta);

    }

    public AlertResponse resolver(
            UUID alertaId
    ) {

        Alerta alerta =
                buscarEntidade(alertaId);

        alerta.resolver();

        repository.salvar(alerta);

        return toResponse(alerta);

    }

    public AlertResponse ignorar(
            UUID alertaId
    ) {

        Alerta alerta =
                buscarEntidade(alertaId);

        alerta.ignorar();

        repository.salvar(alerta);

        return toResponse(alerta);

    }

    public AlertResponse alterarSeveridade(
            UUID id,
            UpdateSeverityRequest request
    ) {

        Alerta alerta =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new AlertaNaoEncontradoException(id)
                        );

        alerta.alterarSeveridade(
                request.severidade()
        );

        repository.salvar(alerta);

        return toResponse(alerta);

    }

    private Alerta buscarEntidade(
            UUID id
    ) {

        return repository.buscarPorId(id)
                .orElseThrow(() ->
                        new AlertaNaoEncontradoException(id)
                );

    }

    private String gerarCodigo() {

        return "ALT-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

    }

    private AlertResponse toResponse(
            Alerta alerta
    ) {

        return new AlertResponse(
                alerta.getId(),
                alerta.getCodigo(),
                alerta.getDispositivoId(),
                alerta.getTelemetriaId(),
                alerta.getTipo(),
                alerta.getSeveridade(),
                alerta.getDescricao(),
                alerta.getStatus(),
                alerta.getGeradoEm(),
                alerta.getResolvidoEm(),
                alerta.getOrigem()
        );

    }

}