package com.traceon.traceonapi.alert.infrastructure.repository;

import com.traceon.traceonapi.alert.domain.entity.Alerta;
import com.traceon.traceonapi.alert.domain.repository.AlertaRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AlertaRepository implements AlertaRepositoryInterface {

    private final List<Alerta> alertas = new ArrayList<>();

    @Override
    public Alerta salvar(
            Alerta alerta
    ) {

        buscarPorId(alerta.getId())
                .ifPresent(alertas::remove);

        alertas.add(alerta);

        return alerta;

    }

    @Override
    public Optional<Alerta> buscarPorId(
            UUID id
    ) {

        return alertas.stream()
                .filter(alerta -> alerta.getId().equals(id))
                .findFirst();

    }

    @Override
    public List<Alerta> buscarTodos() {

        return new ArrayList<>(alertas);

    }

    @Override
    public List<Alerta> buscarPorDispositivo(
            UUID dispositivoId
    ) {

        return alertas.stream()
                .filter(alerta ->
                        alerta.getDispositivoId()
                                .equals(dispositivoId)
                )
                .toList();

    }

}