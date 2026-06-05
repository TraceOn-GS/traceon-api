package com.traceon.traceonapi.occurrence.domain.entity;

import com.traceon.traceonapi.occurrence.domain.enums.StatusOcorrencia;
import com.traceon.traceonapi.occurrence.domain.exception.OperacaoOcorrenciaInvalidaException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
public class Ocorrencia {

    private final UUID id;

    private String codigo;

    private String titulo;

    private String descricao;

    private StatusOcorrencia status;

    private final UUID alertaId;

    private final UUID dispositivoId;

    private String responsavel;

    private final LocalDateTime abertaEm;

    private LocalDateTime encerradaEm;

    public Ocorrencia(
            UUID id,
            String codigo,
            String titulo,
            String descricao,
            UUID alertaId,
            UUID dispositivoId,
            String responsavel
    ) {

        this.id = Objects.requireNonNull(id);

        this.codigo = Objects.requireNonNull(codigo);

        this.titulo = Objects.requireNonNull(titulo);

        this.descricao = Objects.requireNonNull(descricao);

        this.alertaId = Objects.requireNonNull(alertaId);

        this.dispositivoId = Objects.requireNonNull(dispositivoId);

        this.responsavel = responsavel;

        this.status = StatusOcorrencia.ABERTA;

        this.abertaEm = LocalDateTime.now();

    }

    public void iniciarAnalise() {

        validarNaoFinalizada();

        if (status != StatusOcorrencia.ABERTA) {
            throw new OperacaoOcorrenciaInvalidaException(
                    "Somente ocorrências abertas podem entrar em análise."
            );
        }

        status = StatusOcorrencia.EM_ANALISE;

    }

    public void iniciarTratamento() {

        validarNaoFinalizada();

        if (status != StatusOcorrencia.EM_ANALISE) {
            throw new OperacaoOcorrenciaInvalidaException(
                    "Somente ocorrências em análise podem entrar em tratamento."
            );
        }

        status = StatusOcorrencia.EM_TRATAMENTO;

    }

    public void encerrar() {

        validarNaoFinalizada();

        if (status != StatusOcorrencia.EM_TRATAMENTO) {
            throw new OperacaoOcorrenciaInvalidaException(
                    "Somente ocorrências em tratamento podem ser encerradas."
            );
        }

        status = StatusOcorrencia.ENCERRADA;

        encerradaEm = LocalDateTime.now();

    }

    public void cancelar() {

        validarNaoFinalizada();

        status = StatusOcorrencia.CANCELADA;

        encerradaEm = LocalDateTime.now();

    }

    public void atualizarResponsavel(
            String responsavel
    ) {

        validarNaoFinalizada();

        this.responsavel = responsavel;

    }

    public void atualizarDescricao(
            String descricao
    ) {

        validarNaoFinalizada();

        this.descricao = Objects.requireNonNull(descricao);

    }

    private void validarNaoFinalizada() {

        if (status == StatusOcorrencia.ENCERRADA) {
            throw new OperacaoOcorrenciaInvalidaException(
                    "Ocorrência encerrada não pode ser alterada."
            );
        }

        if (status == StatusOcorrencia.CANCELADA) {
            throw new OperacaoOcorrenciaInvalidaException(
                    "Ocorrência cancelada não pode ser alterada."
            );
        }

    }


}