package com.traceon.traceonapi.device.application;

import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;
import com.traceon.traceonapi.device.domain.exception.DispositivoNaoEncontradoException;
import com.traceon.traceonapi.device.domain.repository.DispositivoRepositoryInterface;
import com.traceon.traceonapi.device.dto.CreateDispositivoRequest;
import com.traceon.traceonapi.device.dto.DispositivoResponse;
import com.traceon.traceonapi.device.dto.UpdateDispositivoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DispositivoRepositoryInterface dispositivoRepository;

    private Long sequence = 1L;

    public DeviceService(
            DispositivoRepositoryInterface dispositivoRepository
    ) {
        this.dispositivoRepository = dispositivoRepository;
    }

    public DispositivoResponse create(
            CreateDispositivoRequest request
    ) {

        DispositivoEspacial dispositivo =
                new DispositivoEspacial(
                        sequence++,
                        request.codigoSerial(),
                        request.modelo(),
                        request.energiaAtual()
                );

        return toResponse(
                dispositivoRepository.salvar(dispositivo)
        );

    }

    public List<DispositivoResponse> findAll() {

        return dispositivoRepository.buscarTodos()
                .stream()
                .map(this::toResponse)
                .toList();

    }

    public DispositivoResponse findById(
            Long id
    ) {

        return dispositivoRepository.buscarPorId(id)
                .map(this::toResponse)
                .orElseThrow(() ->
                        new DispositivoNaoEncontradoException(
                                id
                        ));

    }

    public DispositivoResponse update(
            Long id,
            UpdateDispositivoRequest request
    ) {

        DispositivoEspacial dispositivo =
                dispositivoRepository.buscarPorId(id)
                        .orElseThrow(() ->
                                new DispositivoNaoEncontradoException(
                                        id
                                ));

        dispositivo.atualizarDados(
                request.codigoSerial(),
                request.modelo(),
                request.energiaAtual()
        );

        dispositivoRepository.salvar(dispositivo);

        return toResponse(dispositivo);

    }

    public void delete(
            Long id
    ) {

        dispositivoRepository.remover(id);

    }

    public DispositivoResponse ativar(
            Long id
    ) {

        DispositivoEspacial dispositivo =
                dispositivoRepository.buscarPorId(id)
                        .orElseThrow(() ->
                                new DispositivoNaoEncontradoException(
                                        id
                                ));

        dispositivo.ativar();

        dispositivoRepository.salvar(dispositivo);

        return toResponse(dispositivo);

    }

    public DispositivoResponse desativar(
            Long id
    ) {

        DispositivoEspacial dispositivo =
                dispositivoRepository.buscarPorId(id)
                        .orElseThrow(() ->
                                new DispositivoNaoEncontradoException(
                                        id
                                ));

        dispositivo.desativar();

        dispositivoRepository.salvar(dispositivo);

        return toResponse(dispositivo);

    }

    public DispositivoResponse colocarEmManutencao(
            Long id
    ) {

        DispositivoEspacial dispositivo =
                dispositivoRepository.buscarPorId(id)
                        .orElseThrow(() ->
                                new DispositivoNaoEncontradoException(
                                        id
                                ));

        dispositivo.colocarEmManutencao();

        dispositivoRepository.salvar(dispositivo);

        return toResponse(dispositivo);

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