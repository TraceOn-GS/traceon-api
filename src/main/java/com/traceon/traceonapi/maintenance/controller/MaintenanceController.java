package com.traceon.traceonapi.maintenance.controller;

import com.traceon.traceonapi.maintenance.application.MaintenanceService;
import com.traceon.traceonapi.maintenance.dto.CreateChecklistRequest;
import com.traceon.traceonapi.maintenance.dto.CreateMaintenanceRequest;
import com.traceon.traceonapi.maintenance.dto.MaintenanceResponse;
import com.traceon.traceonapi.shared.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService service;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<MaintenanceResponse>>> findAll() {

        List<MaintenanceResponse> manutencoes =
                service.findAll();

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Manutenções encontradas com sucesso.",
                        200,
                        manutencoes
                )
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<MaintenanceResponse>> findById(
            @PathVariable UUID id
    ) {

        MaintenanceResponse manutencao =
                service.findById(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Manutenção encontrada com sucesso.",
                        200,
                        manutencao
                )
        );

    }

    @PostMapping
    public ResponseEntity<SuccessResponse<MaintenanceResponse>> create(
            @Valid
            @RequestBody CreateMaintenanceRequest request
    ) {

        MaintenanceResponse manutencao =
                service.create(request);

        return ResponseEntity.status(201)
                .body(
                        new SuccessResponse<>(
                                "Manutenção criada com sucesso.",
                                201,
                                manutencao
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
                        "Manutenção removida com sucesso.",
                        200,
                        null
                )
        );

    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<SuccessResponse<MaintenanceResponse>> iniciar(
            @PathVariable UUID id
    ) {

        MaintenanceResponse manutencao =
                service.iniciar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Manutenção iniciada com sucesso.",
                        200,
                        manutencao
                )
        );

    }

    @PatchMapping("/{id}/waiting-part")
    public ResponseEntity<SuccessResponse<MaintenanceResponse>> aguardarPeca(
            @PathVariable UUID id
    ) {

        MaintenanceResponse manutencao =
                service.aguardarPeca(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Manutenção aguardando peça.",
                        200,
                        manutencao
                )
        );

    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<SuccessResponse<MaintenanceResponse>> concluir(
            @PathVariable UUID id
    ) {

        MaintenanceResponse manutencao =
                service.concluir(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Manutenção concluída com sucesso.",
                        200,
                        manutencao
                )
        );

    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<SuccessResponse<MaintenanceResponse>> cancelar(
            @PathVariable UUID id
    ) {

        MaintenanceResponse manutencao =
                service.cancelar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Manutenção cancelada com sucesso.",
                        200,
                        manutencao
                )
        );

    }

    @PostMapping("/{id}/checklists")
    public ResponseEntity<SuccessResponse<MaintenanceResponse>> adicionarChecklist(
            @PathVariable UUID id,
            @Valid
            @RequestBody CreateChecklistRequest request
    ) {

        MaintenanceResponse manutencao =
                service.adicionarChecklist(
                        id,
                        request
                );

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Checklist adicionado com sucesso.",
                        200,
                        manutencao
                )
        );

    }

    @PatchMapping("/{maintenanceId}/checklists/{checklistId}/complete")
    public ResponseEntity<SuccessResponse<MaintenanceResponse>> concluirChecklist(
            @PathVariable UUID maintenanceId,
            @PathVariable UUID checklistId
    ) {

        MaintenanceResponse manutencao =
                service.concluirChecklist(
                        maintenanceId,
                        checklistId
                );

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Checklist concluído com sucesso.",
                        200,
                        manutencao
                )
        );

    }

}