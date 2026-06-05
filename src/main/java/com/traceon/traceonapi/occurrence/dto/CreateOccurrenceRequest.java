package com.traceon.traceonapi.occurrence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateOccurrenceRequest(
        @NotBlank(
                message = "Codigo obrigatório"
        )
        String codigo,

        String responsavel,

        @NotBlank(
                message = "Título obrigatório"
        )
        String titulo,

        @NotBlank(
                message = "Descrição obrigatória"
        )
        String descricao,

        @NotNull(
                message = "Id do alerta obrigatório"
        )
        UUID alertaId

) {
}