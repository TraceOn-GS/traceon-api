package com.traceon.traceonapi.telemetry.infrastructure.repository;

import com.traceon.traceonapi.telemetry.domain.entity.Telemetria;
import com.traceon.traceonapi.telemetry.domain.repository.TelemetryRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TelemetryRepository implements TelemetryRepositoryInterface {

    private final List<Telemetria> telemetrias = new ArrayList<>();

    @Override
    public Telemetria salvar(
            Telemetria telemetria
    ) {

        telemetrias.add(telemetria);

        return telemetria;

    }

    @Override
    public Optional<Telemetria> buscarPorId(
            UUID id
    ) {

        return telemetrias.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

    }

    @Override
    public List<Telemetria> buscarPorIdDispositivo(
            UUID dispositivoId
    ) {

        return telemetrias.stream()
                .filter(t ->
                        t.getDispositivoId()
                                .equals(dispositivoId)
                )
                .toList();

    }

    @Override
    public Optional<Telemetria> buscarUltimoPorIdDispositivo(
            UUID dispositivoId
    ) {

        return telemetrias.stream()
                .filter(t ->
                        t.getDispositivoId()
                                .equals(dispositivoId)
                )
                .max(
                        Comparator.comparing(
                                Telemetria::getTimestamp
                        )
                );

    }

}