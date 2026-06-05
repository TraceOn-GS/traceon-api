package com.traceon.traceonapi.device.domain.exception;

import java.util.UUID;

public class DispositivoNaoEncontradoException
        extends RuntimeException {

    public DispositivoNaoEncontradoException(
            UUID id
    ) {

        super(
                "Dispositivo com id "
                        + id
                        + " não encontrado."
        );

    }

}