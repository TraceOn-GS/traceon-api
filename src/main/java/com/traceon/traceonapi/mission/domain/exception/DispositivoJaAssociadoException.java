package com.traceon.traceonapi.mission.domain.exception;

import java.util.UUID;

public class DispositivoJaAssociadoException extends RuntimeException {

    public DispositivoJaAssociadoException(UUID dispositivoId) {
        super("Dispositivo " + dispositivoId + " ja esta associado a missao.");
    }
}

