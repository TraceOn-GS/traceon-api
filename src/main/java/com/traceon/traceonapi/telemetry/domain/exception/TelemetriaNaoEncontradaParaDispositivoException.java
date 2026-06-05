package com.traceon.traceonapi.telemetry.domain.exception;

import java.util.UUID;

public class TelemetriaNaoEncontradaParaDispositivoException
        extends RuntimeException {

    public TelemetriaNaoEncontradaParaDispositivoException(
            UUID dispositivoId
    ) {

        super(
                "Nenhuma telemetria encontrada para o dispositivo: "
                        + dispositivoId
        );

    }

}