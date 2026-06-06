package com.traceon.traceonapi.identity.dto;

import com.traceon.traceonapi.identity.domain.enums.PerfilUsuario;
import com.traceon.traceonapi.identity.domain.enums.StatusUsuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(

        UUID id,

        String nome,

        String email,

        PerfilUsuario perfil,

        StatusUsuario status,

        LocalDateTime ultimoLogin,

        LocalDateTime criadoEm

) {
}