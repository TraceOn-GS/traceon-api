package com.traceon.traceonapi.maintenance.domain.exception;

import java.util.UUID;

public class OcorrenciaNaoAssociadaException
        extends RuntimeException {

    public OcorrenciaNaoAssociadaException(
            UUID ocorrenciaId
    ) {

        super(
                "Ocorrência não encontrada: " + ocorrenciaId
        );

    }

}