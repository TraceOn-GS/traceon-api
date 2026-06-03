package com.traceon.traceonapi.device.controller;

import com.traceon.traceonapi.device.application.DispositivoService;
import com.traceon.traceonapi.device.dto.CreateDispositivoRequest;
import com.traceon.traceonapi.device.dto.DispositivoResponse;
import com.traceon.traceonapi.device.dto.UpdateDispositivoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DispositivoController {

    private final DispositivoService service;

    @PostMapping
    public DispositivoResponse create(
            @RequestBody
            CreateDispositivoRequest request
    ) {

        return service.create(request);

    }

    @GetMapping
    public List<DispositivoResponse> findAll() {

        return service.findAll();

    }

    @GetMapping("/{id}")
    public DispositivoResponse findById(
            @PathVariable Long id
    ) {

        return service.findById(id);

    }

    @PutMapping("/{id}")
    public DispositivoResponse update(
            @PathVariable Long id,
            @RequestBody UpdateDispositivoRequest request
    ) {

        return service.update(id, request);

    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {

        service.delete(id);

    }

    @PatchMapping("/{id}/ativar")
    public DispositivoResponse ativar(
            @PathVariable Long id
    ) {

        return service.ativar(id);

    }

    @PatchMapping("/{id}/desativar")
    public DispositivoResponse desativar(
            @PathVariable Long id
    ) {

        return service.desativar(id);

    }

    @PatchMapping("/{id}/manutencao")
    public DispositivoResponse manutencao(
            @PathVariable Long id
    ) {

        return service.colocarEmManutencao(id);

    }

}