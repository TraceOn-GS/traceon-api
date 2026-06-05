package com.traceon.traceonapi.telemetry.domain.entity;

import com.traceon.traceonapi.telemetry.domain.valueobject.Localizacao;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Telemetria {

    private final UUID id;
    private final UUID dispositivoId;

    private Double temperatura;
    private Double nivelEnergia;
    private Double radiacao;

    private final Localizacao localizacao;
    private final LocalDateTime timestamp;

    public Telemetria(
            UUID id,
            UUID dispositivoId,
            Double temperatura,
            Double nivelEnergia,
            Double radiacao,
            Localizacao localizacao,
            LocalDateTime timestamp
    ) {

        this.id = Objects.requireNonNull(
                id,
                "Id da telemetria obrigatório"
        );

        this.dispositivoId = Objects.requireNonNull(
                dispositivoId,
                "Id do dispositivo obrigatório"
        );

        this.localizacao = Objects.requireNonNull(
                localizacao,
                "Localização obrigatória"
        );

        this.timestamp = Objects.requireNonNull(
                timestamp,
                "Timestamp obrigatório"
        );

        validarEnergia(nivelEnergia);

        this.temperatura = temperatura;
        this.nivelEnergia = nivelEnergia;
        this.radiacao = radiacao;

    }

    private void validarEnergia(Double nivelEnergia) {

        Objects.requireNonNull(
                nivelEnergia,
                "Nível de energia obrigatório"
        );

        if (nivelEnergia < 0 || nivelEnergia > 100) {
            throw new IllegalArgumentException(
                    "Nível de energia deve estar entre 0 e 100"
            );
        }

    }

}