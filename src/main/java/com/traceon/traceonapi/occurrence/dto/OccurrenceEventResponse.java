package com.traceon.traceonapi.occurrence.dto;

import com.traceon.traceonapi.occurrence.domain.enums.TipoEventoOcorrencia;

import java.time.LocalDateTime;
import java.util.UUID;

public record OccurrenceEventResponse(

        UUID id,

        String descricao,

        TipoEventoOcorrencia tipo,

        LocalDateTime ocorridoEm

) {
}