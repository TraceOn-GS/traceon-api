package com.traceon.traceonapi.device.controller;

import com.traceon.traceonapi.device.application.DeviceService;
import com.traceon.traceonapi.device.dto.CreateDispositivoRequest;
import com.traceon.traceonapi.device.dto.DispositivoResponse;
import com.traceon.traceonapi.device.dto.UpdateDispositivoRequest;
import com.traceon.traceonapi.shared.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DispositivoController {

    private final DeviceService service;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<DispositivoResponse>>> findAll() {

        List<DispositivoResponse> dispositivos =
                service.findAll();

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Dispositivos encontrados com sucesso.",
                        200,
                        dispositivos
                )
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<DispositivoResponse>> findById(
            @PathVariable UUID id
    ) {

        DispositivoResponse dispositivo =
                service.findById(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Dispositivo encontrado com sucesso.",
                        200,
                        dispositivo
                )
        );

    }

    @PostMapping
    public ResponseEntity<SuccessResponse<DispositivoResponse>> create(
            @Valid
            @RequestBody
            CreateDispositivoRequest request
    ) {

        DispositivoResponse dispositivo =
                service.create(request);

        return ResponseEntity.status(201)
                .body(
                        new SuccessResponse<>(
                                "Dispositivo criado com sucesso.",
                                201,
                                dispositivo
                        )
                );

    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<DispositivoResponse>> update(
            @PathVariable UUID id,
            @Valid
            @RequestBody UpdateDispositivoRequest request
    ) {

        DispositivoResponse dispositivo =
                service.update(id, request);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Dispositivo atualizado com sucesso.",
                        200,
                        dispositivo
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
                        "Dispositivo removido com sucesso.",
                        200,
                        null
                )
        );

    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<SuccessResponse<DispositivoResponse>> ativar(
            @PathVariable UUID id
    ) {

        DispositivoResponse dispositivo =
                service.ativar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Dispositivo ativado com sucesso.",
                        200,
                        dispositivo
                )
        );

    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<SuccessResponse<DispositivoResponse>> desativar(
            @PathVariable UUID id
    ) {

        DispositivoResponse dispositivo =
                service.desativar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Dispositivo desativado com sucesso.",
                        200,
                        dispositivo
                )
        );

    }

    @PatchMapping("/{id}/manutencao")
    public ResponseEntity<SuccessResponse<DispositivoResponse>> manutencao(
            @PathVariable UUID id
    ) {

        DispositivoResponse dispositivo =
                service.colocarEmManutencao(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Dispositivo colocado em manutenção com sucesso.",
                        200,
                        dispositivo
                )
        );

    }

}