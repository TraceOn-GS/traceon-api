package com.traceon.traceonapi.mission.domain.entity;

import com.traceon.traceonapi.mission.domain.enums.TipoEventoMissao;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class EventoMissao {

    private final UUID id;
    private final String titulo;
    private final String descricao;
    private final LocalDateTime timestamp;
    private final TipoEventoMissao tipo;

    public EventoMissao(
            UUID id,
            String titulo,
            String descricao,
            LocalDateTime timestamp,
            TipoEventoMissao tipo
    ) {
        this.id = Objects.requireNonNull(id, "Id do evento obrigatorio");
        this.titulo = Objects.requireNonNull(titulo, "Titulo do evento obrigatorio");
        this.descricao = Objects.requireNonNull(descricao, "Descricao do evento obrigatoria");
        this.timestamp = Objects.requireNonNull(timestamp, "Timestamp do evento obrigatorio");
        this.tipo = Objects.requireNonNull(tipo, "Tipo do evento obrigatorio");
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TipoEventoMissao getTipo() {
        return tipo;
    }
}

