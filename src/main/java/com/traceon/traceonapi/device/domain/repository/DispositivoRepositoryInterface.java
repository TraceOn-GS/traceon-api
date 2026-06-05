package com.traceon.traceonapi.device.domain.repository;
import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DispositivoRepositoryInterface {

    Optional<DispositivoEspacial> buscarPorId(UUID id);

    List<DispositivoEspacial> buscarTodos();

    DispositivoEspacial salvar(
            DispositivoEspacial dispositivo
    );
    void remover(UUID id);
}