package com.traceon.traceonapi.telemetry.domain.exception;

public class QualidadeSinalInvalidaException
        extends IllegalArgumentException {

  public QualidadeSinalInvalidaException() {

    super(
            "Qualidade do sinal deve estar entre 0 e 100."
    );

  }

}