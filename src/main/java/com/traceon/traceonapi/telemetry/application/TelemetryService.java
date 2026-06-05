package com.traceon.traceonapi.telemetry.application;

import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;
import com.traceon.traceonapi.device.domain.repository.DispositivoRepositoryInterface;
import com.traceon.traceonapi.telemetry.domain.entity.Telemetria;
import com.traceon.traceonapi.telemetry.domain.exception.DispositivoNaoEncontradoParaTelemetriaException;
import com.traceon.traceonapi.telemetry.domain.exception.TelemetriaNaoEncontradaParaDispositivoException;
import com.traceon.traceonapi.telemetry.domain.repository.TelemetryRepositoryInterface;
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

    private final DispositivoRepositoryInterface dispositivoRepository;

    public TelemetryService(
            TelemetryRepositoryInterface repository,
            DispositivoRepositoryInterface dispositivoRepository
    ) {

        this.repository = repository;
        this.dispositivoRepository = dispositivoRepository;

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
                        request.temperatura(),
                        request.nivelEnergia(),
                        request.radiacao(),
                        localizacao,
                        LocalDateTime.now()
                );

        repository.salvar(telemetria);

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
                telemetria.getTemperatura(),
                telemetria.getNivelEnergia(),
                telemetria.getRadiacao(),
                new LocationResponse(
                        telemetria.getLocalizacao().getLatitude(),
                        telemetria.getLocalizacao().getLongitude(),
                        telemetria.getLocalizacao().getAltitude()
                ),
                telemetria.getTimestamp()
        );

    }

}