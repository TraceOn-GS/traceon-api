package com.traceon.traceonapi.maintenance.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record MaintenanceStartedEvent(
        UUID manutencaoId,
        LocalDateTime ocorridoEm
) {
}