package com.traceon.traceonapi.occurrence.domain.exception;

public class OperacaoOcorrenciaInvalidaException
        extends RuntimeException {

    public OperacaoOcorrenciaInvalidaException(
            String mensagem
    ) {

        super(mensagem);

    }

}