package com.traceon.traceonapi.device.domain.exception;

public class DispositivoNaoEncontradoException
        extends RuntimeException {

    public DispositivoNaoEncontradoException(
            Long id
    ) {

        super(
                "Dispositivo com id "
                        + id
                        + " não encontrado."
        );

    }

}