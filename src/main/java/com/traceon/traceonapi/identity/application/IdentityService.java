package com.traceon.traceonapi.identity.application;

import com.traceon.traceonapi.identity.domain.entity.Usuario;
import com.traceon.traceonapi.identity.domain.exception.EmailJaCadastradoException;
import com.traceon.traceonapi.identity.domain.exception.UsuarioNaoEncontradoException;
import com.traceon.traceonapi.identity.domain.repository.UsuarioRepositoryInterface;
import com.traceon.traceonapi.identity.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IdentityService {

    private final UsuarioRepositoryInterface repository;

    public IdentityService(
            UsuarioRepositoryInterface repository
    ) {
        this.repository = repository;
    }

    public UserResponse create(
            CreateUserRequest request
    ) {

        repository.buscarPorEmail(
                request.email()
        ).ifPresent(usuario -> {
            throw new EmailJaCadastradoException(
                    request.email()
            );
        });

        Usuario usuario =
                new Usuario(
                        UUID.randomUUID(),
                        request.nome(),
                        request.email(),
                        request.senha(),
                        request.perfil()
                );

        repository.salvar(usuario);

        return toResponse(usuario);

    }

    public List<UserSummaryResponse> findAll() {

        return repository.buscarTodos()
                .stream()
                .map(this::toSummaryResponse)
                .toList();

    }

    public UserResponse findById(
            UUID id
    ) {

        return repository.buscarPorId(id)
                .map(this::toResponse)
                .orElseThrow(() ->
                        new UsuarioNaoEncontradoException(id)
                );

    }

    public UserResponse update(
            UUID id,
            UpdateUserRequest request
    ) {

        Usuario usuario =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new UsuarioNaoEncontradoException(id)
                        );

        usuario.alterarNome(
                request.nome()
        );

        usuario.alterarEmail(
                request.email()
        );

        usuario.alterarPerfil(
                request.perfil()
        );

        repository.atualizar(usuario);

        return toResponse(usuario);

    }

    public void delete(
            UUID id
    ) {

        repository.buscarPorId(id)
                .orElseThrow(() ->
                        new UsuarioNaoEncontradoException(id)
                );

        repository.remover(id);

    }

    public UserResponse ativar(
            UUID id
    ) {

        Usuario usuario =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new UsuarioNaoEncontradoException(id)
                        );

        usuario.ativar();

        repository.atualizar(usuario);

        return toResponse(usuario);

    }

    public UserResponse desativar(
            UUID id
    ) {

        Usuario usuario =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new UsuarioNaoEncontradoException(id)
                        );

        usuario.desativar();

        repository.atualizar(usuario);

        return toResponse(usuario);

    }

    public UserResponse alterarSenha(
            UUID id,
            UpdatePasswordRequest request
    ) {

        Usuario usuario =
                repository.buscarPorId(id)
                        .orElseThrow(() ->
                                new UsuarioNaoEncontradoException(id)
                        );

        usuario.alterarSenha(
                request.senhaAtual(),
                request.novaSenha()
        );

        repository.atualizar(usuario);

        return toResponse(usuario);

    }

    public UserResponse login(
            LoginRequest request
    ) {

        Usuario usuario =
                repository.buscarPorEmail(
                        request.email()
                ).orElseThrow(() ->
                        new UsuarioNaoEncontradoException(
                                UUID.randomUUID()
                        )
                );

        if (!usuario.getSenha()
                .equals(request.senha())) {

            throw new IllegalArgumentException(
                    "Senha inválida"
            );

        }

        usuario.registrarLogin();

        repository.atualizar(usuario);

        return toResponse(usuario);

    }

    private UserResponse toResponse(
            Usuario usuario
    ) {

        return new UserResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.getStatus(),
                usuario.getUltimoLogin(),
                usuario.getCriadoEm()
        );

    }

    private UserSummaryResponse toSummaryResponse(
            Usuario usuario
    ) {

        return new UserSummaryResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.getStatus()
        );

    }

}