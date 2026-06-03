package com.traceon.traceonapi.device.dto;

public record UpdateDispositivoRequest(
        String codigoSerial,
        String modelo,
        Double energiaAtual
) {
}
