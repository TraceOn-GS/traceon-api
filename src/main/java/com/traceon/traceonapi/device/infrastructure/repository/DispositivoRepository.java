package com.traceon.traceonapi.device.infrastructure.repository;
import com.traceon.traceonapi.device.domain.entity.DispositivoEspacial;
import com.traceon.traceonapi.device.domain.repository.DispositivoRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DispositivoRepository implements DispositivoRepositoryInterface {

    private final List<DispositivoEspacial> dispositivos = new ArrayList<>();

    @Override
    public Optional<DispositivoEspacial> buscarPorId(Long id){
        return dispositivos.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<DispositivoEspacial> buscarTodos() {
        return dispositivos;
    }


    @Override
    public DispositivoEspacial salvar(
            DispositivoEspacial dispositivo
    ) {

        Optional<DispositivoEspacial> existente =
                buscarPorId(dispositivo.getId());

        if(existente.isPresent()){

            dispositivos.remove(existente.get());

        }

        dispositivos.add(dispositivo);

        return dispositivo;

    }

    @Override
    public void deletarPorId(Long id){
        dispositivos.removeIf(d -> d.getId().equals(id));
    }
}
