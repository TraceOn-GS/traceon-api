package com.traceon.traceonapi.mission.domain.entity;

import com.traceon.traceonapi.mission.domain.enums.PrioridadeMissao;
import com.traceon.traceonapi.mission.domain.enums.StatusMissao;
import com.traceon.traceonapi.mission.domain.enums.TipoEventoMissao;
import com.traceon.traceonapi.mission.domain.exception.DispositivoJaAssociadoException;
import com.traceon.traceonapi.mission.domain.exception.DispositivoNaoAssociadoException;
import com.traceon.traceonapi.mission.domain.exception.OperacaoMissaoInvalidaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Missao {

    private final UUID id;
    private String codigo;
    private String nome;
    private String objetivo;
    private String descricao;
    private PrioridadeMissao prioridade;
    private StatusMissao status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFimPrevista;
    private LocalDateTime dataEncerramento;
    private final LocalDateTime criadoEm;
    private final List<EventoMissao> eventos = new ArrayList<>();
    private final Set<UUID> dispositivosAssociados = new LinkedHashSet<>();

    public Missao(
            UUID id,
            String codigo,
            String nome,
            String objetivo,
            String descricao,
            PrioridadeMissao prioridade,
            LocalDateTime dataFimPrevista
    ) {
        this.id = Objects.requireNonNull(id, "Id da missao obrigatorio");
        this.codigo = Objects.requireNonNull(codigo, "Codigo obrigatorio");
        this.nome = Objects.requireNonNull(nome, "Nome obrigatorio");
        this.objetivo = Objects.requireNonNull(objetivo, "Objetivo obrigatorio");
        this.descricao = Objects.requireNonNull(descricao, "Descricao obrigatoria");
        this.prioridade = Objects.requireNonNull(prioridade, "Prioridade obrigatoria");
        this.dataFimPrevista = Objects.requireNonNull(dataFimPrevista, "Data fim prevista obrigatoria");
        this.status = StatusMissao.PLANEJADA;
        this.criadoEm = LocalDateTime.now();
    }

    public void iniciar() {
        validarPodeAlterar();

        if (status != StatusMissao.PLANEJADA && status != StatusMissao.PAUSADA) {
            throw new OperacaoMissaoInvalidaException("A missao nao pode iniciar no status atual.");
        }

        if (dataInicio == null) {
            dataInicio = LocalDateTime.now();
        }

        status = StatusMissao.EM_EXECUCAO;
        registrarEvento("Missao iniciada", "A missao foi iniciada.", TipoEventoMissao.INICIO);
    }

    public void pausar() {
        validarPodeAlterar();

        if (status != StatusMissao.EM_EXECUCAO) {
            throw new OperacaoMissaoInvalidaException("A missao so pode ser pausada em execucao.");
        }

        status = StatusMissao.PAUSADA;
        registrarEvento("Missao pausada", "A missao foi pausada.", TipoEventoMissao.MANUTENCAO);
    }

    public void finalizar() {
        validarPodeAlterar();

        if (status != StatusMissao.EM_EXECUCAO) {
            throw new OperacaoMissaoInvalidaException("A missao so pode ser finalizada em execucao.");
        }

        status = StatusMissao.FINALIZADA;
        dataEncerramento = LocalDateTime.now();
        registrarEvento("Missao finalizada", "A missao foi finalizada.", TipoEventoMissao.FINALIZACAO);
    }

    public void cancelar() {
        validarPodeAlterar();

        status = StatusMissao.CANCELADA;
        dataEncerramento = LocalDateTime.now();
        registrarEvento("Missao cancelada", "A missao foi cancelada.", TipoEventoMissao.ALERTA);
    }

    public void registrarEvento(String titulo, String descricao, TipoEventoMissao tipo) {
        eventos.add(new EventoMissao(UUID.randomUUID(), titulo, descricao, LocalDateTime.now(), tipo));
    }

    public void atualizarDados(
            String codigo,
            String nome,
            String objetivo,
            String descricao,
            PrioridadeMissao prioridade,
            LocalDateTime dataFimPrevista
    ) {
        validarPodeAlterar();

        this.codigo = Objects.requireNonNull(codigo, "Codigo obrigatorio");
        this.nome = Objects.requireNonNull(nome, "Nome obrigatorio");
        this.objetivo = Objects.requireNonNull(objetivo, "Objetivo obrigatorio");
        this.descricao = Objects.requireNonNull(descricao, "Descricao obrigatoria");
        this.prioridade = Objects.requireNonNull(prioridade, "Prioridade obrigatoria");
        this.dataFimPrevista = Objects.requireNonNull(dataFimPrevista, "Data fim prevista obrigatoria");
    }

    public void associarDispositivo(UUID dispositivoId) {
        validarPodeAlterar();

        UUID id = Objects.requireNonNull(dispositivoId, "Id do dispositivo obrigatorio");
        if (!dispositivosAssociados.add(id)) {
            throw new DispositivoJaAssociadoException(id);
        }
    }

    public void desassociarDispositivo(UUID dispositivoId) {
        validarPodeAlterar();

        UUID id = Objects.requireNonNull(dispositivoId, "Id do dispositivo obrigatorio");
        if (!dispositivosAssociados.remove(id)) {
            throw new DispositivoNaoAssociadoException(id);
        }
    }

    private void validarPodeAlterar() {
        if (status == StatusMissao.FINALIZADA) {
            throw new OperacaoMissaoInvalidaException("A missao finalizada nao pode ser alterada.");
        }

        if (status == StatusMissao.CANCELADA) {
            throw new OperacaoMissaoInvalidaException("A missao cancelada nao pode ser alterada.");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public PrioridadeMissao getPrioridade() {
        return prioridade;
    }

    public StatusMissao getStatus() {
        return status;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataFimPrevista() {
        return dataFimPrevista;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public List<EventoMissao> getEventos() {
        return Collections.unmodifiableList(eventos);
    }

    public Set<UUID> getDispositivosAssociados() {
        return Collections.unmodifiableSet(dispositivosAssociados);
    }
}

