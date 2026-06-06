package com.traceon.traceonapi.maintenance.domain.exception;

public class OperacaoManutencaoInvalidaException
        extends RuntimeException {

    public OperacaoManutencaoInvalidaException(
            String mensagem
    ) {

        super(mensagem);

    }

}