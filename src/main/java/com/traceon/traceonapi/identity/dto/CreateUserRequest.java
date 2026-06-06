package com.traceon.traceonapi.identity.dto;

import com.traceon.traceonapi.identity.domain.enums.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(

        @NotBlank(
                message = "Nome obrigatório"
        )
        String nome,

        @Email(
                message = "Email inválido"
        )
        @NotBlank(
                message = "Email obrigatório"
        )
        String email,

        @NotBlank(
                message = "Senha obrigatória"
        )
        String senha,

        @NotNull(
                message = "Perfil obrigatório"
        )
        PerfilUsuario perfil

) {
}