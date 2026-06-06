package com.traceon.traceonapi.identity.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequest(

        @NotBlank(
                message = "Senha atual obrigatória"
        )
        String senhaAtual,

        @NotBlank(
                message = "Nova senha obrigatória"
        )
        String novaSenha

) {
}