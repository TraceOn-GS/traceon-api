package com.traceon.traceonapi.alert.dto;

import com.traceon.traceonapi.alert.domain.enums.OrigemAlerta;
import com.traceon.traceonapi.alert.domain.enums.SeveridadeAlerta;
import com.traceon.traceonapi.alert.domain.enums.TipoAlerta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateAlertRequest(

        @NotNull
        UUID dispositivoId,

        UUID telemetriaId,

        @NotNull
        TipoAlerta tipo,

        @NotNull
        SeveridadeAlerta severidade,

        @NotBlank
        String descricao,

        @NotNull
        OrigemAlerta origem

) {
}