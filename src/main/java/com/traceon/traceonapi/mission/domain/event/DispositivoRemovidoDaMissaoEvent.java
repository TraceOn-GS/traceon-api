package com.traceon.traceonapi.mission.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record DispositivoRemovidoDaMissaoEvent(
        UUID missaoId,
        UUID dispositivoId,
        LocalDateTime ocorridoEm
) {
}

