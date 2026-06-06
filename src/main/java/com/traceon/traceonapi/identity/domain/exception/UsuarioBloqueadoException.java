package com.traceon.traceonapi.identity.domain.exception;

import java.util.UUID;

public class UsuarioBloqueadoException
        extends RuntimeException {

    public UsuarioBloqueadoException(
            UUID id
    ) {

        super(
                "Usuário está bloqueado. Id: "
                        + id
        );

    }

}