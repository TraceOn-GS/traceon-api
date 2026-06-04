package com.traceon.traceonapi.device.infrastructure.repository;
import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;
import com.traceon.traceonapi.device.domain.repository.DispositivoRepositoryInterface;
import com.traceon.traceonapi.device.dto.DispositivoResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DispositivoRepository implements DispositivoRepositoryInterface {

    private final List<DispositivoEspacial> dispositivos = new ArrayList<>();

    @Override
    public Optional<DispositivoEspacial> findById(Long id){
        return dispositivos.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<DispositivoEspacial> findAll() {
        return dispositivos;
    }

    @Override
    public DispositivoEspacial save(DispositivoEspacial dispositivo){
        dispositivos.add(dispositivo);
        return dispositivo;
    }

    @Override
    public void deleteById(Long id){
        dispositivos.removeIf(d -> d.getId().equals(id));
    }
}
