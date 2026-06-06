package com.traceon.traceonapi.identity.domain.entity;

import com.traceon.traceonapi.identity.domain.enums.PerfilUsuario;
import com.traceon.traceonapi.identity.domain.enums.StatusUsuario;
import com.traceon.traceonapi.identity.domain.exception.UsuarioBloqueadoException;
import com.traceon.traceonapi.identity.domain.exception.UsuarioInativoException;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Usuario {

    private final UUID id;

    private String nome;

    private String email;

    private String senha;

    private PerfilUsuario perfil;

    private StatusUsuario status;

    private LocalDateTime ultimoLogin;

    private final LocalDateTime criadoEm;

    public Usuario(
            UUID id,
            String nome,
            String email,
            String senha,
            PerfilUsuario perfil
    ) {

        this.id = Objects.requireNonNull(
                id,
                "Id obrigatório"
        );

        this.nome = Objects.requireNonNull(
                nome,
                "Nome obrigatório"
        );

        this.email = Objects.requireNonNull(
                email,
                "Email obrigatório"
        );

        this.senha = Objects.requireNonNull(
                senha,
                "Senha obrigatória"
        );

        this.perfil = Objects.requireNonNull(
                perfil,
                "Perfil obrigatório"
        );

        this.status = StatusUsuario.ATIVO;

        this.criadoEm = LocalDateTime.now();

    }

    public void alterarNome(
            String nome
    ) {

        this.nome = Objects.requireNonNull(
                nome,
                "Nome obrigatório"
        );

    }

    public void alterarEmail(
            String email
    ) {

        this.email = Objects.requireNonNull(
                email,
                "Email obrigatório"
        );

    }

    public void alterarSenha(
            String senhaAtual,
            String novaSenha
    ) {

        if (!senha.equals(senhaAtual)) {
            throw new IllegalArgumentException(
                    "Senha atual inválida"
            );
        }

        this.senha = Objects.requireNonNull(
                novaSenha,
                "Nova senha obrigatória"
        );

    }

    public void alterarPerfil(
            PerfilUsuario perfil
    ) {

        this.perfil = Objects.requireNonNull(
                perfil,
                "Perfil obrigatório"
        );

    }

    public void ativar() {

        this.status = StatusUsuario.ATIVO;

    }

    public void desativar() {

        this.status = StatusUsuario.INATIVO;

    }

    public void bloquear() {

        this.status = StatusUsuario.BLOQUEADO;

    }

    public void registrarLogin() {

        if (status == StatusUsuario.INATIVO) {
            throw new UsuarioInativoException(
                    id
            );
        }

        if (status == StatusUsuario.BLOQUEADO) {
            throw new UsuarioBloqueadoException(
                    id
            );
        }

        this.ultimoLogin = LocalDateTime.now();

    }

}