package com.traceon.traceonapi.device.domain.repository;
import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;

import java.util.List;
import java.util.Optional;

public interface DispositivoRepository {

    Optional<DispositivoEspacial> findById(Long id);

    List<DispositivoEspacial> findAll();

    DispositivoEspacial save(
            DispositivoEspacial dispositivo
    );

}