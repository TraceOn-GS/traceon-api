package com.traceon.traceonapi.alert.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record AlertaGeradoEvent(

        UUID alertaId,

        UUID dispositivoId,

        LocalDateTime ocorridoEm

) {
}