package com.traceon.traceonapi.alert.domain.exception;

import java.util.UUID;

public class AlertaJaResolvidoException extends RuntimeException {

  public AlertaJaResolvidoException(
          UUID id
  ) {

    super(
            "O alerta já foi resolvido: " + id
    );

  }

}