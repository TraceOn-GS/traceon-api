package com.traceon.traceonapi.telemetry.controller;

import com.traceon.traceonapi.shared.dto.SuccessResponse;
import com.traceon.traceonapi.telemetry.application.TelemetryService;
import com.traceon.traceonapi.telemetry.dto.CreateTelemetryRequest;
import com.traceon.traceonapi.telemetry.dto.TelemetryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices/{deviceId}/telemetry")
@RequiredArgsConstructor
public class TelemetryController {

    private final TelemetryService service;

    @PostMapping
    public ResponseEntity<SuccessResponse<TelemetryResponse>> registrarTelemetria(
            @PathVariable UUID deviceId,
            @Valid
            @RequestBody
            CreateTelemetryRequest request
    ) {

        TelemetryResponse telemetria =
                service.registrarTelemetria(
                        deviceId,
                        request
                );

        return ResponseEntity.status(201)
                .body(
                        new SuccessResponse<>(
                                "Telemetria registrada com sucesso.",
                                201,
                                telemetria
                        )
                );

    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<TelemetryResponse>>> buscarHistorico(
            @PathVariable UUID deviceId
    ) {

        List<TelemetryResponse> telemetrias =
                service.buscarHistorico(deviceId);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Histórico de telemetria encontrado com sucesso.",
                        200,
                        telemetrias
                )
        );

    }

    @GetMapping("/latest")
    public ResponseEntity<SuccessResponse<TelemetryResponse>> buscarUltimaTelemetria(
            @PathVariable UUID deviceId
    ) {

        TelemetryResponse telemetria =
                service.buscarUltimaTelemetria(deviceId);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Última telemetria encontrada com sucesso.",
                        200,
                        telemetria
                )
        );

    }

}