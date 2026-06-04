package com.traceon.traceonapi.device.domain.repository;
import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;

import java.util.List;
import java.util.Optional;

public interface DispositivoRepositoryInterface {

    Optional<DispositivoEspacial> buscarPorId(Long id);

    List<DispositivoEspacial> buscarTodos();

    DispositivoEspacial salvar(
            DispositivoEspacial dispositivo
    );
    void deletarPorId(Long id);
}