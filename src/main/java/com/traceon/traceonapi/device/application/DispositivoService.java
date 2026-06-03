package com.traceon.traceonapi.device.application;

import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;
import com.traceon.traceonapi.device.domain.enums.StatusDispositivo;
import com.traceon.traceonapi.device.dto.CreateDispositivoRequest;
import com.traceon.traceonapi.device.dto.DispositivoResponse;
import com.traceon.traceonapi.device.dto.UpdateDispositivoRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DispositivoService {

    private final List<DispositivoEspacial> dispositivos =
            new ArrayList<>();

    private Long sequence = 1L;

    public DispositivoResponse create(
            CreateDispositivoRequest request
    ) {

        DispositivoEspacial dispositivo =
                new DispositivoEspacial();

        dispositivo.setId(sequence++);

        dispositivo.setCodigoSerial(
                request.codigoSerial()
        );

        dispositivo.setModelo(
                request.modelo()
        );

        dispositivo.setEnergiaAtual(
                request.energiaAtual()
        );

        dispositivo.setStatus(
                StatusDispositivo.OPERACIONAL
        );

        dispositivos.add(dispositivo);

        return toResponse(dispositivo);

    }

    public List<DispositivoResponse> findAll() {

        return dispositivos.stream()
                .map(this::toResponse)
                .toList();

    }

    public DispositivoResponse findById(
            Long id
    ) {

        return dispositivos.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .map(this::toResponse)
                .orElseThrow();

    }

    public DispositivoResponse update(
            Long id,
            UpdateDispositivoRequest request
    ) {

        DispositivoEspacial dispositivo =
                getDispositivo(id);

        dispositivo.setCodigoSerial(
                request.codigoSerial()
        );

        dispositivo.setModelo(
                request.modelo()
        );

        dispositivo.setEnergiaAtual(
                request.energiaAtual()
        );

        return toResponse(dispositivo);

    }

    public void delete(Long id) {

        dispositivos.removeIf(
                d -> d.getId().equals(id)
        );

    }

    public DispositivoResponse ativar(
            Long id
    ) {

        DispositivoEspacial dispositivo =
                getDispositivo(id);

        dispositivo.setStatus(
                StatusDispositivo.OPERACIONAL
        );

        return toResponse(dispositivo);

    }

    public DispositivoResponse desativar(
            Long id
    ) {

        DispositivoEspacial dispositivo =
                getDispositivo(id);

        dispositivo.setStatus(
                StatusDispositivo.DESATIVADO
        );

        return toResponse(dispositivo);

    }

    public DispositivoResponse colocarEmManutencao(
            Long id
    ) {

        DispositivoEspacial dispositivo =
                getDispositivo(id);

        dispositivo.setStatus(
                StatusDispositivo.MANUTENCAO
        );

        return toResponse(dispositivo);

    }

    private DispositivoEspacial getDispositivo(
            Long id
    ) {

        return dispositivos.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow();

    }

    private DispositivoResponse toResponse(
            DispositivoEspacial dispositivo
    ) {

        return new DispositivoResponse(

                dispositivo.getId(),

                dispositivo.getCodigoSerial(),

                dispositivo.getModelo(),

                dispositivo.getEnergiaAtual(),

                dispositivo.getStatus()

        );

    }

}