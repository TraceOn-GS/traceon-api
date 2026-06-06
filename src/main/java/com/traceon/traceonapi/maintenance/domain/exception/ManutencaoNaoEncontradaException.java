package com.traceon.traceonapi.maintenance.domain.exception;

import java.util.UUID;

public class ManutencaoNaoEncontradaException
        extends RuntimeException {

    public ManutencaoNaoEncontradaException(
            UUID id
    ) {

        super(
                "Manutenção não encontrada: " + id
        );

    }

}