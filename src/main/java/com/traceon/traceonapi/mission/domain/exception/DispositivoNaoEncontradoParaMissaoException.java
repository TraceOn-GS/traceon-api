package com.traceon.traceonapi.mission.domain.exception;

import java.util.UUID;

public class DispositivoNaoEncontradoParaMissaoException extends RuntimeException {

    public DispositivoNaoEncontradoParaMissaoException(UUID dispositivoId) {
        super("Dispositivo " + dispositivoId + " nao encontrado para a missao.");
    }
}

