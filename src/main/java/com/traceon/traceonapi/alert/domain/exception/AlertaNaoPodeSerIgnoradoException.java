package com.traceon.traceonapi.alert.domain.exception;

import java.util.UUID;

public class AlertaNaoPodeSerIgnoradoException extends RuntimeException {

    public AlertaNaoPodeSerIgnoradoException(
            UUID id
    ) {

        super(
                "Um alerta resolvido não pode ser ignorado: " + id
        );

    }

}