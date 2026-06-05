package com.traceon.traceonapi.occurrence.dto;

import com.traceon.traceonapi.alert.domain.enums.SeveridadeAlerta;
import com.traceon.traceonapi.occurrence.domain.enums.StatusOcorrencia;

import java.time.LocalDateTime;
import java.util.UUID;

public record OccurrenceSummaryResponse(

        UUID id,

        String codigo,

        String titulo,

        SeveridadeAlerta severidade,

        StatusOcorrencia status,

        LocalDateTime criadaEm

) {
}