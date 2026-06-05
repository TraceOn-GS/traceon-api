package com.traceon.traceonapi.alert.dto;

import com.traceon.traceonapi.alert.domain.enums.OrigemAlerta;
import com.traceon.traceonapi.alert.domain.enums.SeveridadeAlerta;
import com.traceon.traceonapi.alert.domain.enums.StatusAlerta;
import com.traceon.traceonapi.alert.domain.enums.TipoAlerta;

import java.time.LocalDateTime;
import java.util.UUID;

public record AlertResponse(

        UUID id,

        String codigo,

        UUID dispositivoId,

        UUID telemetriaId,

        TipoAlerta tipo,

        SeveridadeAlerta severidade,

        String descricao,

        StatusAlerta status,

        LocalDateTime geradoEm,

        LocalDateTime resolvidoEm,

        OrigemAlerta origem

) {
}