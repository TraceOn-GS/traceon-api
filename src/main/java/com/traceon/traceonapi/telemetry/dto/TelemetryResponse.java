package com.traceon.traceonapi.telemetry.dto;

import com.traceon.traceonapi.telemetry.dto.LocationResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public record TelemetryResponse(

        UUID id,

        UUID dispositivoId,

        Double temperaturaInterna,

        Double temperaturaExterna,

        Double nivelEnergia,

        Double radiacao,

        Double qualidadeSinal,

        LocationResponse localizacao,

        LocalDateTime timestamp

) {
}