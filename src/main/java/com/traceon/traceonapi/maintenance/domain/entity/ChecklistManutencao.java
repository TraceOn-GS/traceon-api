package com.traceon.traceonapi.maintenance.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class ChecklistManutencao {

    private final UUID id;

    private final String descricao;

    private boolean concluido;

    private LocalDateTime concluidoEm;

    public ChecklistManutencao(
            UUID id,
            String descricao
    ) {

        this.id = id;
        this.descricao = descricao;

    }

    public void concluir() {

        this.concluido = true;

        this.concluidoEm = LocalDateTime.now();

    }


}