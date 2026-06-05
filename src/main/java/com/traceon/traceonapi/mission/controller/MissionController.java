package com.traceon.traceonapi.mission.controller;

import com.traceon.traceonapi.mission.application.MissionApplicationService;
import com.traceon.traceonapi.mission.dto.CreateMissionRequest;
import com.traceon.traceonapi.mission.dto.MissionDeviceResponse;
import com.traceon.traceonapi.mission.dto.MissionResponse;
import com.traceon.traceonapi.mission.dto.MissionSummaryResponse;
import com.traceon.traceonapi.mission.dto.UpdateMissionRequest;
import com.traceon.traceonapi.shared.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionApplicationService service;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<MissionSummaryResponse>>> findAll() {
        List<MissionSummaryResponse> missoes = service.findAll();

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Missoes encontradas com sucesso.",
                        200,
                        missoes
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<MissionResponse>> findById(@PathVariable UUID id) {

        MissionResponse missao = service.findById(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Missao encontrada com sucesso.",
                        200,
                        missao
                )
        );
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<MissionResponse>> create(
            @Valid @RequestBody CreateMissionRequest request
    ) {
        MissionResponse missao = service.create(request);

        return ResponseEntity.status(201)
                .body(
                        new SuccessResponse<>(
                                "Missao criada com sucesso.",
                                201,
                                missao
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<MissionResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateMissionRequest request
    ) {
        MissionResponse missao = service.update(id, request);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Missao atualizada com sucesso.",
                        200,
                        missao
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> delete(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Missao removida com sucesso.",
                        200,
                        null
                )
        );
    }

    @PatchMapping("/{id}/iniciar")
    public ResponseEntity<SuccessResponse<MissionResponse>> iniciar(@PathVariable UUID id) {
        MissionResponse missao = service.iniciar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Missao iniciada com sucesso.",
                        200,
                        missao
                )
        );
    }

    @PatchMapping("/{id}/pausar")
    public ResponseEntity<SuccessResponse<MissionResponse>> pausar(@PathVariable UUID id) {
        MissionResponse missao = service.pausar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Missao pausada com sucesso.",
                        200,
                        missao
                )
        );
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<SuccessResponse<MissionResponse>> finalizar(@PathVariable UUID id) {
        MissionResponse missao = service.finalizar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Missao finalizada com sucesso.",
                        200,
                        missao
                )
        );
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<SuccessResponse<MissionResponse>> cancelar(@PathVariable UUID id) {
        MissionResponse missao = service.cancelar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Missao cancelada com sucesso.",
                        200,
                        missao
                )
        );
    }

    @GetMapping("/{missionId}/devices")
    public ResponseEntity<SuccessResponse<List<MissionDeviceResponse>>> listarDispositivos(
            @PathVariable UUID missionId
    ) {
        List<MissionDeviceResponse> dispositivos = service.listarDispositivos(missionId);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Dispositivos da missao encontrados com sucesso.",
                        200,
                        dispositivos
                )
        );
    }

    @PatchMapping("/{missionId}/devices/{deviceId}")
    public ResponseEntity<SuccessResponse<MissionResponse>> associarDispositivo(
            @PathVariable UUID missionId,
            @PathVariable UUID deviceId
    ) {
        MissionResponse missao = service.associarDispositivo(missionId, deviceId);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Dispositivo associado a missao com sucesso.",
                        200,
                        missao
                )
        );
    }

    @DeleteMapping("/{missionId}/devices/{deviceId}")
    public ResponseEntity<SuccessResponse<MissionResponse>> desassociarDispositivo(
            @PathVariable UUID missionId,
            @PathVariable UUID deviceId
    ) {
        MissionResponse missao = service.desassociarDispositivo(missionId, deviceId);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Dispositivo removido da missao com sucesso.",
                        200,
                        missao
                )
        );
    }
}
