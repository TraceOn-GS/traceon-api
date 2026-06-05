package com.traceon.traceonapi.telemetry.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TelemetryResponse(

        UUID id,

        UUID dispositivoId,

        Double temperatura,

        Double nivelEnergia,

        Double radiacao,

        LocationResponse localizacao,

        LocalDateTime timestamp

) {
}