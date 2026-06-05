package com.traceon.traceonapi.mission.dto;

import com.traceon.traceonapi.mission.domain.enums.PrioridadeMissao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateMissionRequest(
        @NotBlank(message = "Codigo obrigatorio")
        String codigo,
        @NotBlank(message = "Nome obrigatorio")
        String nome,
        @NotBlank(message = "Objetivo obrigatorio")
        String objetivo,
        @NotBlank(message = "Descricao obrigatoria")
        String descricao,
        @NotNull(message = "Prioridade obrigatoria")
        PrioridadeMissao prioridade,
        @NotNull(message = "Data fim prevista obrigatoria")
        LocalDateTime dataFimPrevista
) {
}

