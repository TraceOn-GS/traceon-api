package com.traceon.traceonapi.maintenance.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateMaintenanceRequest(

        @NotBlank(
                message = "Título obrigatório"
        )
        String titulo,

        @NotBlank(
                message = "Descrição obrigatória"
        )
        String descricao,

        String responsavel

) {
}