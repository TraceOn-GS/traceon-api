package com.traceon.traceonapi.device.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateDispositivoRequest(
        @NotBlank(
                message =
                        "Código serial obrigatório"
        )
        String codigoSerial,
        @NotBlank(
                message =
                        "Modelo obrigatório"
        )
        String modelo,
        @NotNull(
                message =
                        "Energia obrigatória"
        )
        @Min(
                value = 0,
                message =
                        "Energia mínima é 0"
        )
        @Max(
                value = 100,
                message =
                        "Energia máxima é 100"
        )
        Double energiaAtual
) {
}
