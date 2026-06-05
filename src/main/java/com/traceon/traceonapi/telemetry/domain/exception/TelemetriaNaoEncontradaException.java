package com.traceon.traceonapi.telemetry.domain.exception;

import java.util.UUID;

public class TelemetriaNaoEncontradaException
        extends RuntimeException {

    public TelemetriaNaoEncontradaException(
            UUID id
    ) {

        super(
                "Telemetria não encontrada: "
                        + id
        );

    }

}