package com.traceon.traceonapi.alert.domain.entity;

import com.traceon.traceonapi.alert.domain.enums.OrigemAlerta;
import com.traceon.traceonapi.alert.domain.enums.SeveridadeAlerta;
import com.traceon.traceonapi.alert.domain.enums.StatusAlerta;
import com.traceon.traceonapi.alert.domain.enums.TipoAlerta;
import com.traceon.traceonapi.alert.domain.exception.AlertaJaResolvidoException;
import com.traceon.traceonapi.alert.domain.exception.AlertaNaoPodeSerIgnoradoException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Alerta {

    private final UUID id;
    private final String codigo;

    private final UUID dispositivoId;
    private final UUID telemetriaId;

    private TipoAlerta tipo;
    private SeveridadeAlerta severidade;

    private String descricao;

    private StatusAlerta status;

    private final LocalDateTime geradoEm;
    private LocalDateTime resolvidoEm;

    private final OrigemAlerta origem;

    public Alerta(
            UUID id,
            String codigo,
            UUID dispositivoId,
            UUID telemetriaId,
            TipoAlerta tipo,
            SeveridadeAlerta severidade,
            String descricao,
            OrigemAlerta origem
    ) {

        this.id = Objects.requireNonNull(id);
        this.codigo = Objects.requireNonNull(codigo);

        this.dispositivoId = Objects.requireNonNull(dispositivoId);
        this.telemetriaId = telemetriaId;

        this.tipo = Objects.requireNonNull(tipo);
        this.severidade = Objects.requireNonNull(severidade);

        this.descricao = Objects.requireNonNull(descricao);

        this.origem = Objects.requireNonNull(origem);

        this.status = StatusAlerta.ABERTO;
        this.geradoEm = LocalDateTime.now();

    }

    public void iniciarAnalise() {

        if (status == StatusAlerta.RESOLVIDO) {
            throw new AlertaJaResolvidoException(id);
        }

        status = StatusAlerta.EM_ANALISE;

    }

    public void resolver() {

        if (status == StatusAlerta.RESOLVIDO) {
            throw new AlertaJaResolvidoException(id);
        }

        status = StatusAlerta.RESOLVIDO;
        resolvidoEm = LocalDateTime.now();

    }

    public void ignorar() {

        if (status == StatusAlerta.RESOLVIDO) {
            throw new AlertaNaoPodeSerIgnoradoException(id);
        }

        status = StatusAlerta.IGNORADO;

    }

    public void alterarSeveridade(
            SeveridadeAlerta severidade
    ) {

        this.severidade =
                Objects.requireNonNull(severidade);

    }
}