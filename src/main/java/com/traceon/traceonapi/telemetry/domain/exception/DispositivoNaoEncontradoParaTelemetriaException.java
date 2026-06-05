package com.traceon.traceonapi.telemetry.domain.exception;

import java.util.UUID;

public class DispositivoNaoEncontradoParaTelemetriaException
        extends RuntimeException {

    public DispositivoNaoEncontradoParaTelemetriaException(
            UUID dispositivoId
    ) {

        super(
                "Dispositivo não encontrado para telemetria: "
                        + dispositivoId
        );

    }

}