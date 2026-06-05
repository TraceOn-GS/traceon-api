package com.traceon.traceonapi.mission.domain.repository;

import com.traceon.traceonapi.mission.domain.entity.Missao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MissionRepositoryInterface {

    List<Missao> findAll();

    Optional<Missao> findById(UUID id);

    Missao save(Missao missao);

    Missao update(Missao missao);

    void delete(UUID id);
}

