package com.traceon.traceonapi.maintenance.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateChecklistRequest(

        @NotBlank(
                message = "Descrição obrigatória"
        )
        String descricao

) {
}