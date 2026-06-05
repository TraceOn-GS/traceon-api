package com.traceon.traceonapi.mission.domain.exception;

import java.util.UUID;

public class DispositivoDesativadoParaMissaoException extends RuntimeException {

    public DispositivoDesativadoParaMissaoException(UUID dispositivoId) {
        super("Dispositivo " + dispositivoId + " esta desativado e nao pode ser associado.");
    }
}

