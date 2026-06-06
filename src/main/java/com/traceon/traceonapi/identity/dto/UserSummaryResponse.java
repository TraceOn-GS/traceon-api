package com.traceon.traceonapi.identity.dto;

import com.traceon.traceonapi.identity.domain.enums.PerfilUsuario;
import com.traceon.traceonapi.identity.domain.enums.StatusUsuario;

import java.util.UUID;

public record UserSummaryResponse(

        UUID id,

        String nome,

        String email,

        PerfilUsuario perfil,

        StatusUsuario status

) {
}