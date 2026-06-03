package com.traceon.traceonapi.device.dto;

public record CreateDispositivoRequest(
        String codigoSerial,
        String modelo,
        Double energiaAtual
) {
}
