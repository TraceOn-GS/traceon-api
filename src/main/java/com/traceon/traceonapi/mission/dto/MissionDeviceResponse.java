package com.traceon.traceonapi.mission.dto;

import com.traceon.traceonapi.device.domain.enums.StatusDispositivo;

import java.util.UUID;

public record MissionDeviceResponse(
        UUID id,
        String codigoSerial,
        String modelo,
        Double energiaAtual,
        StatusDispositivo status
) {
}

