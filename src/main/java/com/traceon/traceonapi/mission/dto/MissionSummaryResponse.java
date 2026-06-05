package com.traceon.traceonapi.mission.dto;

import com.traceon.traceonapi.mission.domain.enums.PrioridadeMissao;
import com.traceon.traceonapi.mission.domain.enums.StatusMissao;

import java.time.LocalDateTime;
import java.util.UUID;

public record MissionSummaryResponse(
        UUID id,
        String codigo,
        String nome,
        PrioridadeMissao prioridade,
        StatusMissao status,
        LocalDateTime dataInicio,
        LocalDateTime dataFimPrevista
) {
}

