package com.traceon.traceonapi.telemetry.domain.entity;

import com.traceon.traceonapi.telemetry.domain.exception.QualidadeSinalInvalidaException;
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

    private Double temperaturaInterna;
    private Double temperaturaExterna;
    private Double qualidadeSinal;
    private Double nivelEnergia;
    private Double radiacao;

    private final Localizacao localizacao;
    private final LocalDateTime timestamp;

    public Telemetria(
            UUID id,
            UUID dispositivoId,
            Double temperaturaInterna,
            Double temperaturaExterna,
            Double nivelEnergia,
            Double radiacao,
            Double qualidadeSinal,
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
        validarQualidadeSinal(qualidadeSinal);

        this.temperaturaInterna = temperaturaInterna;
        this.temperaturaExterna = temperaturaExterna;
        this.nivelEnergia = nivelEnergia;
        this.radiacao = radiacao;
        this.qualidadeSinal = qualidadeSinal;

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
    private void validarQualidadeSinal(
            Double qualidadeSinal
    ) {

        Objects.requireNonNull(
                qualidadeSinal,
                "Qualidade do sinal obrigatória"
        );

        if (qualidadeSinal < 0 || qualidadeSinal > 100) {
            throw new QualidadeSinalInvalidaException();
        }

    }
}