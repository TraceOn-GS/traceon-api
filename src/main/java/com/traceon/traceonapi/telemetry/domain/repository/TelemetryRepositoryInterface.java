package com.traceon.traceonapi.telemetry.domain.repository;

import com.traceon.traceonapi.telemetry.domain.entity.Telemetria;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TelemetryRepositoryInterface {

    Telemetria salvar(
            Telemetria telemetria
    );

    Optional<Telemetria> buscarPorId(
            UUID id
    );

    List<Telemetria> buscarPorIdDispositivo(
            UUID dispositivoId
    );

    Optional<Telemetria> buscarUltimoPorIdDispositivo(
            UUID dispositivoId
    );

}