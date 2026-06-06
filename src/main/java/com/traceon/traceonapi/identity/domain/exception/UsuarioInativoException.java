package com.traceon.traceonapi.identity.domain.exception;

import java.util.UUID;

public class UsuarioInativoException
        extends RuntimeException {

    public UsuarioInativoException(
            UUID id
    ) {

        super(
                "Usuário está inativo. Id: "
                        + id
        );

    }

}