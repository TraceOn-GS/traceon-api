package com.traceon.traceonapi.telemetry.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record TelemetryReceivedEvent(

        UUID telemetriaId,

        UUID dispositivoId,

        LocalDateTime ocorridoEm

) {
}