package com.traceon.traceonapi.mission.domain.exception;

import java.util.UUID;

public class DispositivoNaoAssociadoException extends RuntimeException {

    public DispositivoNaoAssociadoException(UUID dispositivoId) {
        super("Dispositivo " + dispositivoId + " nao esta associado a missao.");
    }
}

