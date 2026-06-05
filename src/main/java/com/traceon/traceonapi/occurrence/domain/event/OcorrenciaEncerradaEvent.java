package com.traceon.traceonapi.occurrence.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record OcorrenciaEncerradaEvent(

        UUID ocorrenciaId,

        LocalDateTime ocorridoEm

) {
}