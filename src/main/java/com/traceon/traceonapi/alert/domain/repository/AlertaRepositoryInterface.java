package com.traceon.traceonapi.alert.domain.repository;

import com.traceon.traceonapi.alert.domain.entity.Alerta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlertaRepositoryInterface {

    Alerta salvar(
            Alerta alerta
    );

    Optional<Alerta> buscarPorId(
            UUID id
    );

    List<Alerta> buscarTodos();

    List<Alerta> buscarPorDispositivo(
            UUID dispositivoId
    );

}