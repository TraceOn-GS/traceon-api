package com.traceon.traceonapi.identity.infrastructure.repository;

import com.traceon.traceonapi.identity.domain.entity.Usuario;
import com.traceon.traceonapi.identity.domain.repository.UsuarioRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsuarioRepository
        implements UsuarioRepositoryInterface {

    private final Map<UUID, Usuario> usuarios =
            new LinkedHashMap<>();

    @Override
    public Usuario salvar(
            Usuario usuario
    ) {

        usuarios.put(
                usuario.getId(),
                usuario
        );

        return usuario;

    }

    @Override
    public Usuario atualizar(
            Usuario usuario
    ) {

        usuarios.put(
                usuario.getId(),
                usuario
        );

        return usuario;

    }

    @Override
    public Optional<Usuario> buscarPorId(
            UUID id
    ) {

        return Optional.ofNullable(
                usuarios.get(id)
        );

    }

    @Override
    public Optional<Usuario> buscarPorEmail(
            String email
    ) {

        return usuarios.values()
                .stream()
                .filter(usuario ->
                        usuario.getEmail()
                                .equalsIgnoreCase(email)
                )
                .findFirst();

    }

    @Override
    public List<Usuario> buscarTodos() {

        return new ArrayList<>(
                usuarios.values()
        );

    }

    @Override
    public void remover(
            UUID id
    ) {

        usuarios.remove(id);

    }

}