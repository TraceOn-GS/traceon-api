package com.traceon.traceonapi.occurrence.dto;

import jakarta.validation.constraints.NotBlank;

public record ResolveOccurrenceRequest(

        @NotBlank(
                message = "Descrição da resolução obrigatória"
        )
        String descricaoResolucao

) {
}