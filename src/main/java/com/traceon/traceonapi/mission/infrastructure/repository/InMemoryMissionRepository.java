package com.traceon.traceonapi.mission.infrastructure.repository;

import com.traceon.traceonapi.mission.domain.entity.Missao;
import com.traceon.traceonapi.mission.domain.repository.MissionRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryMissionRepository implements MissionRepositoryInterface {

    private final Map<UUID, Missao> missoes = new LinkedHashMap<>();

    @Override
    public List<Missao> findAll() {
        return new ArrayList<>(missoes.values());
    }

    @Override
    public Optional<Missao> findById(UUID id) {
        return Optional.ofNullable(missoes.get(id));
    }

    @Override
    public Missao save(Missao missao) {
        missoes.put(missao.getId(), missao);
        return missao;
    }

    @Override
    public Missao update(Missao missao) {
        missoes.put(missao.getId(), missao);
        return missao;
    }

    @Override
    public void delete(UUID id) {
        missoes.remove(id);
    }
}

