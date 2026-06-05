package com.traceon.traceonapi.telemetry.application;

import com.traceon.traceonapi.alert.domain.entity.Alerta;
import com.traceon.traceonapi.alert.domain.repository.AlertaRepositoryInterface;
import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;
import com.traceon.traceonapi.device.domain.repository.DispositivoRepositoryInterface;
import com.traceon.traceonapi.telemetry.domain.entity.Telemetria;
import com.traceon.traceonapi.telemetry.domain.exception.DispositivoNaoEncontradoParaTelemetriaException;
import com.traceon.traceonapi.telemetry.domain.exception.TelemetriaNaoEncontradaParaDispositivoException;
import com.traceon.traceonapi.telemetry.domain.repository.TelemetryRepositoryInterface;
import com.traceon.traceonapi.telemetry.domain.service.TelemetryAnalysisService;
import com.traceon.traceonapi.telemetry.domain.valueobject.Localizacao;
import com.traceon.traceonapi.telemetry.dto.CreateTelemetryRequest;
import com.traceon.traceonapi.telemetry.dto.LocationResponse;
import com.traceon.traceonapi.telemetry.dto.TelemetryResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TelemetryService {

    private final TelemetryRepositoryInterface repository;
    private final AlertaRepositoryInterface alertRepository;

    private final TelemetryAnalysisService telemetryAnalysisService;
    private final DispositivoRepositoryInterface dispositivoRepository;

    public TelemetryService(
            TelemetryRepositoryInterface repository,
            DispositivoRepositoryInterface dispositivoRepository,
            AlertaRepositoryInterface alertRepository
    ) {

        this.repository = repository;
        this.dispositivoRepository = dispositivoRepository;
        this.alertRepository = alertRepository;

        this.telemetryAnalysisService =
                new TelemetryAnalysisService();

    }

    public TelemetryResponse registrarTelemetria(
            UUID dispositivoId,
            CreateTelemetryRequest request
    ) {

        DispositivoEspacial dispositivo =
                dispositivoRepository.buscarPorId(dispositivoId)
                        .orElseThrow(() ->
                                new DispositivoNaoEncontradoParaTelemetriaException(
                                        dispositivoId
                                )
                        );

        Localizacao localizacao =
                new Localizacao(
                        request.localizacao().latitude(),
                        request.localizacao().longitude(),
                        request.localizacao().altitude()
                );

        Telemetria telemetria =
                new Telemetria(
                        UUID.randomUUID(),
                        dispositivo.getId(),
                        request.temperaturaInterna(),
                        request.temperaturaExterna(),
                        request.nivelEnergia(),
                        request.radiacao(),
                        request.qualidadeSinal(),
                        localizacao,
                        LocalDateTime.now()
                );

        repository.salvar(telemetria);

        List<Alerta> alertasGerados =
                telemetryAnalysisService.analisar(
                        telemetria
                );

        alertasGerados.forEach(
                alertRepository::salvar
        );

        return toResponse(telemetria);

    }
    public List<TelemetryResponse> buscarHistorico(
            UUID dispositivoId
    ) {

        dispositivoRepository.buscarPorId(dispositivoId)
                .orElseThrow(() ->
                        new DispositivoNaoEncontradoParaTelemetriaException(
                                dispositivoId
                        )
                );

        return repository
                .buscarPorIdDispositivo(dispositivoId)
                .stream()
                .map(this::toResponse)
                .toList();

    }

    public TelemetryResponse buscarUltimaTelemetria(
            UUID dispositivoId
    ) {

        dispositivoRepository.buscarPorId(dispositivoId)
                .orElseThrow(() ->
                        new DispositivoNaoEncontradoParaTelemetriaException(
                                dispositivoId
                        )
                );

        Telemetria telemetria =
                repository.buscarUltimoPorIdDispositivo(
                                dispositivoId
                        )
                        .orElseThrow(() ->
                                new TelemetriaNaoEncontradaParaDispositivoException(
                                        dispositivoId
                                )
                        );

        return toResponse(telemetria);

    }

    private TelemetryResponse toResponse(
            Telemetria telemetria
    ) {

        return new TelemetryResponse(
                telemetria.getId(),
                telemetria.getDispositivoId(),
                telemetria.getTemperaturaInterna(),
                telemetria.getTemperaturaExterna(),
                telemetria.getNivelEnergia(),
                telemetria.getRadiacao(),
                telemetria.getQualidadeSinal(),
                new LocationResponse(
                        telemetria.getLocalizacao().getLatitude(),
                        telemetria.getLocalizacao().getLongitude(),
                        telemetria.getLocalizacao().getAltitude()
                ),
                telemetria.getTimestamp()
        );

    }

}