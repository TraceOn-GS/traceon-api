package com.traceon.traceonapi.device.dto;

import com.traceon.traceonapi.device.domain.enums.StatusDispositivo;

public record DispositivoResponse(

        Long id,

        String codigoSerial,

        String modelo,

        Double energiaAtual,

        StatusDispositivo status

) {
}