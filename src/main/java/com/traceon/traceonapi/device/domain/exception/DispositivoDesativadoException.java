package com.traceon.traceonapi.device.domain.exception;

public class DispositivoDesativadoException extends RuntimeException {
  public DispositivoDesativadoException() {
      super("O dispositivo está desativado!");
  }
}
