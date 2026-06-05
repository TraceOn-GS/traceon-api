package com.traceon.traceonapi.mission.domain.exception;

import java.util.UUID;

public class MissaoNaoEncontradaException extends RuntimeException {

    public MissaoNaoEncontradaException(UUID id) {
        super("Missao com id " + id + " nao encontrada.");
    }
}

