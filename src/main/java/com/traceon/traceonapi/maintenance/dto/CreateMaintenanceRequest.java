package com.traceon.traceonapi.maintenance.dto;

import com.traceon.traceonapi.maintenance.domain.enums.TipoManutencao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateMaintenanceRequest(

        @NotBlank(
                message = "Código obrigatório"
        )
        String codigo,

        @NotBlank(
                message = "Título obrigatório"
        )
        String titulo,

        @NotBlank(
                message = "Descrição obrigatória"
        )
        String descricao,

        @NotNull(
                message = "Dispositivo obrigatório"
        )
        UUID dispositivoId,

        UUID ocorrenciaId,

        @NotNull(
                message = "Tipo obrigatório"
        )
        TipoManutencao tipo,

        String responsavel

) {
}