package com.traceon.traceonapi.telemetry.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateTelemetryRequest(

        @NotNull(
                message = "Temperatura obrigatória"
        )
        Double temperatura,

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

        @Valid
        @NotNull(
                message = "Localização obrigatória"
        )
        LocationRequest localizacao

) {
}