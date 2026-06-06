package com.traceon.traceonapi.identity.domain.repository;

import com.traceon.traceonapi.identity.domain.entity.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositoryInterface {

    Usuario salvar(
            Usuario usuario
    );

    Usuario atualizar(
            Usuario usuario
    );

    Optional<Usuario> buscarPorId(
            UUID id
    );

    Optional<Usuario> buscarPorEmail(
            String email
    );

    List<Usuario> buscarTodos();

    void remover(
            UUID id
    );

}