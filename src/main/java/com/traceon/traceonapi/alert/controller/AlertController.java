package com.traceon.traceonapi.alert.controller;

import com.traceon.traceonapi.alert.application.AlertService;
import com.traceon.traceonapi.alert.dto.AlertResponse;
import com.traceon.traceonapi.alert.dto.CreateAlertRequest;
import com.traceon.traceonapi.alert.dto.UpdateSeverityRequest;
import com.traceon.traceonapi.shared.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService service;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<AlertResponse>>> findAll() {

        List<AlertResponse> alertas =
                service.findAll();

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Alertas encontrados com sucesso.",
                        200,
                        alertas
                )
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<AlertResponse>> findById(
            @PathVariable UUID id
    ) {

        AlertResponse alerta =
                service.findById(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Alerta encontrado com sucesso.",
                        200,
                        alerta
                )
        );

    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<SuccessResponse<List<AlertResponse>>> findByDevice(
            @PathVariable UUID deviceId
    ) {

        List<AlertResponse> alertas =
                service.findByDevice(deviceId);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Alertas do dispositivo encontrados com sucesso.",
                        200,
                        alertas
                )
        );

    }

    @PostMapping
    public ResponseEntity<SuccessResponse<AlertResponse>> create(
            @Valid
            @RequestBody
            CreateAlertRequest request
    ) {

        AlertResponse alerta =
                service.create(request);

        return ResponseEntity.status(201)
                .body(
                        new SuccessResponse<>(
                                "Alerta criado com sucesso.",
                                201,
                                alerta
                        )
                );

    }

    @PatchMapping("/{id}/analise")
    public ResponseEntity<SuccessResponse<AlertResponse>> iniciarAnalise(
            @PathVariable UUID id
    ) {

        AlertResponse alerta =
                service.iniciarAnalise(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Alerta colocado em análise.",
                        200,
                        alerta
                )
        );

    }

    @PatchMapping("/{id}/resolver")
    public ResponseEntity<SuccessResponse<AlertResponse>> resolver(
            @PathVariable UUID id
    ) {

        AlertResponse alerta =
                service.resolver(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Alerta resolvido com sucesso.",
                        200,
                        alerta
                )
        );

    }

    @PatchMapping("/{id}/ignorar")
    public ResponseEntity<SuccessResponse<AlertResponse>> ignorar(
            @PathVariable UUID id
    ) {

        AlertResponse alerta =
                service.ignorar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Alerta ignorado com sucesso.",
                        200,
                        alerta
                )
        );

    }

    @PatchMapping("/{id}/severidade")
    public ResponseEntity<SuccessResponse<AlertResponse>> alterarSeveridade(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateSeverityRequest request
    ) {

        AlertResponse alerta =
                service.alterarSeveridade(
                        id,
                        request
                );

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Severidade alterada com sucesso.",
                        200,
                        alerta
                )
        );

    }

}