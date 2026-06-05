package com.traceon.traceonapi.alert.domain.exception;

import java.util.UUID;

public class AlertaNaoEncontradoException extends RuntimeException {

    public AlertaNaoEncontradoException(
            UUID id
    ) {

        super(
                "Alerta não encontrado: " + id
        );

    }

}