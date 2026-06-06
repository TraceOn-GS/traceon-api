package com.traceon.traceonapi.identity.controller;

import com.traceon.traceonapi.identity.application.IdentityService;
import com.traceon.traceonapi.identity.dto.*;
import com.traceon.traceonapi.shared.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IdentityService service;

    @PostMapping
    public ResponseEntity<SuccessResponse<UserResponse>> create(
            @Valid
            @RequestBody CreateUserRequest request
    ) {

        return ResponseEntity.status(201)
                .body(
                        new SuccessResponse<>(
                                "Usuário criado com sucesso.",
                                201,
                                service.create(request)
                        )
                );

    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<UserSummaryResponse>>> findAll() {

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Usuários encontrados com sucesso.",
                        200,
                        service.findAll()
                )
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<UserResponse>> findById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Usuário encontrado com sucesso.",
                        200,
                        service.findById(id)
                )
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<UserResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUserRequest request
    ) {

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Usuário atualizado com sucesso.",
                        200,
                        service.update(id, request)
                )
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> delete(
            @PathVariable UUID id
    ) {

        service.delete(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Usuário removido com sucesso.",
                        200,
                        null
                )
        );

    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<SuccessResponse<UserResponse>> ativar(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Usuário ativado com sucesso.",
                        200,
                        service.ativar(id)
                )
        );

    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<SuccessResponse<UserResponse>> desativar(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Usuário desativado com sucesso.",
                        200,
                        service.desativar(id)
                )
        );

    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<SuccessResponse<UserResponse>> alterarSenha(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePasswordRequest request
    ) {

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Senha alterada com sucesso.",
                        200,
                        service.alterarSenha(id, request)
                )
        );

    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<UserResponse>> login(
            @Valid @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Login realizado com sucesso.",
                        200,
                        service.login(request)
                )
        );

    }

}