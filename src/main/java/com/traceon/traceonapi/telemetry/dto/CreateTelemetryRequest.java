package com.traceon.traceonapi.telemetry.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateTelemetryRequest(

        @NotNull(
                message = "Temperatura interna obrigatória"
        )
        Double temperaturaInterna,

        @NotNull(
                message = "Temperatura externa obrigatória"
        )
        Double temperaturaExterna,

        @NotNull(
                message = "Nível de energia obrigatório"
        )
        @Min(
                value = 0,
                message = "Nível mínimo de energia é 0"
        )
        @Max(
                value = 100,
                message = "Nível máximo de energia é 100"
        )
        Double nivelEnergia,

        @NotNull(
                message = "Radiação obrigatória"
        )
        Double radiacao,
        @NotNull(
                message = "Qualidade do sinal obrigatória"
        )
        @Min(
                value = 0,
                message = "Qualidade mínima do sinal é 0"
        )
        @Max(
                value = 100,
                message = "Qualidade máxima do sinal é 100"
        )
        Double qualidadeSinal,
        @Valid
        @NotNull(
                message = "Localização obrigatória"
        )
        LocationRequest localizacao

) {
}