package com.traceon.traceonapi.alert.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record AlertaResolvidoEvent(

        UUID alertaId,

        LocalDateTime ocorridoEm

) {
}