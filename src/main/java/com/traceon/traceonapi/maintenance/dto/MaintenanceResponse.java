package com.traceon.traceonapi.maintenance.dto;

import com.traceon.traceonapi.maintenance.domain.enums.StatusManutencao;
import com.traceon.traceonapi.maintenance.domain.enums.TipoManutencao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MaintenanceResponse(

        UUID id,

        String codigo,

        String titulo,

        String descricao,

        UUID dispositivoId,

        UUID ocorrenciaId,

        TipoManutencao tipo,

        StatusManutencao status,

        String responsavel,

        LocalDateTime abertaEm,

        LocalDateTime iniciadaEm,

        LocalDateTime concluidaEm,

        LocalDateTime canceladaEm,

        List<ChecklistResponse> checklists

) {
}