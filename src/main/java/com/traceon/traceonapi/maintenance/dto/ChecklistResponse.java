package com.traceon.traceonapi.maintenance.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChecklistResponse(

        UUID id,

        String descricao,

        boolean concluido,

        LocalDateTime concluidoEm

) {
}