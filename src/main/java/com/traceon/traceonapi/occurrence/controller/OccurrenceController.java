package com.traceon.traceonapi.occurrence.controller;

import com.traceon.traceonapi.occurrence.application.OccurrenceService;
import com.traceon.traceonapi.occurrence.dto.CreateOccurrenceRequest;
import com.traceon.traceonapi.occurrence.dto.OccurrenceResponse;
import com.traceon.traceonapi.occurrence.dto.UpdateOccurrenceRequest;
import com.traceon.traceonapi.shared.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/occurrences")
@RequiredArgsConstructor
public class OccurrenceController {

    private final OccurrenceService service;

    @PostMapping
    public ResponseEntity<SuccessResponse<OccurrenceResponse>> create(
            @Valid
            @RequestBody
            CreateOccurrenceRequest request
    ) {

        OccurrenceResponse occurrence =
                service.create(request);

        return ResponseEntity.status(201)
                .body(
                        new SuccessResponse<>(
                                "Ocorrência criada com sucesso.",
                                201,
                                occurrence
                        )
                );

    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<OccurrenceResponse>>> findAll() {

        List<OccurrenceResponse> occurrences =
                service.findAll();

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Ocorrências encontradas com sucesso.",
                        200,
                        occurrences
                )
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<OccurrenceResponse>> findById(
            @PathVariable UUID id
    ) {

        OccurrenceResponse occurrence =
                service.findById(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Ocorrência encontrada com sucesso.",
                        200,
                        occurrence
                )
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<OccurrenceResponse>> update(
            @PathVariable UUID id,
            @Valid
            @RequestBody
            UpdateOccurrenceRequest request
    ) {

        OccurrenceResponse occurrence =
                service.update(id, request);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Ocorrência atualizada com sucesso.",
                        200,
                        occurrence
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
                        "Ocorrência removida com sucesso.",
                        200,
                        null
                )
        );

    }

    @PatchMapping("/{id}/iniciar-analise")
    public ResponseEntity<SuccessResponse<OccurrenceResponse>> iniciarAnalise(
            @PathVariable UUID id
    ) {

        OccurrenceResponse occurrence =
                service.iniciarAnalise(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Ocorrência movida para análise.",
                        200,
                        occurrence
                )
        );

    }

    @PatchMapping("/{id}/iniciar-tratamento")
    public ResponseEntity<SuccessResponse<OccurrenceResponse>> iniciarTratamento(
            @PathVariable UUID id
    ) {

        OccurrenceResponse occurrence =
                service.iniciarTratamento(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Tratamento da ocorrência iniciado.",
                        200,
                        occurrence
                )
        );

    }

    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<SuccessResponse<OccurrenceResponse>> encerrar(
            @PathVariable UUID id
    ) {

        OccurrenceResponse occurrence =
                service.encerrar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Ocorrência encerrada com sucesso.",
                        200,
                        occurrence
                )
        );

    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<SuccessResponse<OccurrenceResponse>> cancelar(
            @PathVariable UUID id
    ) {

        OccurrenceResponse occurrence =
                service.cancelar(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        "Ocorrência cancelada com sucesso.",
                        200,
                        occurrence
                )
        );

    }

}