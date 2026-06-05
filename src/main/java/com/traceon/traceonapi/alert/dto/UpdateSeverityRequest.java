package com.traceon.traceonapi.alert.dto;

import com.traceon.traceonapi.alert.domain.enums.SeveridadeAlerta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateSeverityRequest(

        @NotNull(
                message = "Severidade obrigatória"
        )
        SeveridadeAlerta severidade
) {
}