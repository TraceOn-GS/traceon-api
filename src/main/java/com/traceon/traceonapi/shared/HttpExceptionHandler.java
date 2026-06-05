package com.traceon.traceonapi.shared;


import com.traceon.traceonapi.device.domain.exception.DispositivoDesativadoException;
import com.traceon.traceonapi.device.domain.exception.DispositivoNaoEncontradoException;
import com.traceon.traceonapi.mission.domain.exception.MissaoNaoEncontradaException;
import com.traceon.traceonapi.mission.domain.exception.OperacaoMissaoInvalidaException;
import com.traceon.traceonapi.shared.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler {
//    GLOBAL EXCEPTIONS ---------------

    @ExceptionHandler({
            IllegalArgumentException.class,
    })
    public ResponseEntity<ErrorResponse> illegalArgument(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(ex.getMessage(), 400)
        );
    }

    @ExceptionHandler({
            IllegalStateException.class,
    })
    public ResponseEntity<ErrorResponse> illegalState(IllegalStateException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(ex.getMessage(), 400)
        );
    }

//  DEVICE EXCEPTIONS -------------

    @ExceptionHandler(
            DispositivoNaoEncontradoException.class
    )
    public ResponseEntity<ErrorResponse> handleDispositivoNaoEncontrado(
            DispositivoNaoEncontradoException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                404
                        )
                );

    }


    @ExceptionHandler(
            DispositivoDesativadoException.class
    )
    public ResponseEntity<ErrorResponse> handleDispositivoDesativado(
            DispositivoDesativadoException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                400
                        )
                );

    }

//  MISSION EXCEPTIONS -------------

    @ExceptionHandler(
            MissaoNaoEncontradaException.class
    )
    public ResponseEntity<ErrorResponse> handleMissaoNaoEncontrada(
            MissaoNaoEncontradaException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                404
                        )
                );

    }

    @ExceptionHandler(
            OperacaoMissaoInvalidaException.class
    )
    public ResponseEntity<ErrorResponse> handleOperacaoMissaoInvalida(
            OperacaoMissaoInvalidaException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                400
                        )
                );

    }

}