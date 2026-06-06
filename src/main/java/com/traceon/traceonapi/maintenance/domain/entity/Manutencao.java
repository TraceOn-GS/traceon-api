package com.traceon.traceonapi.maintenance.domain.entity;

import com.traceon.traceonapi.maintenance.domain.enums.StatusManutencao;
import com.traceon.traceonapi.maintenance.domain.enums.TipoManutencao;
import com.traceon.traceonapi.maintenance.domain.exception.ChecklistPendenteException;
import com.traceon.traceonapi.maintenance.domain.exception.OperacaoManutencaoInvalidaException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
public class Manutencao {

    private final UUID id;

    private String codigo;

    private String titulo;

    private String descricao;

    private final UUID dispositivoId;

    private final UUID ocorrenciaId;

    private TipoManutencao tipo;

    private StatusManutencao status;

    private String responsavel;

    private final LocalDateTime abertaEm;

    private LocalDateTime iniciadaEm;

    private LocalDateTime concluidaEm;

    private LocalDateTime canceladaEm;

    private final List<ChecklistManutencao> checklists =
            new ArrayList<>();

    public Manutencao(
            UUID id,
            String codigo,
            String titulo,
            String descricao,
            UUID dispositivoId,
            UUID ocorrenciaId,
            TipoManutencao tipo,
            String responsavel
    ) {

        this.id = Objects.requireNonNull(
                id,
                "Id obrigatório"
        );

        this.codigo = Objects.requireNonNull(
                codigo,
                "Código obrigatório"
        );

        this.titulo = Objects.requireNonNull(
                titulo,
                "Título obrigatório"
        );

        this.descricao = Objects.requireNonNull(
                descricao,
                "Descrição obrigatória"
        );

        this.dispositivoId = Objects.requireNonNull(
                dispositivoId,
                "Dispositivo obrigatório"
        );

        this.ocorrenciaId = ocorrenciaId;

        this.tipo = Objects.requireNonNull(
                tipo,
                "Tipo obrigatório"
        );

        this.responsavel = responsavel;

        this.status = StatusManutencao.ABERTA;

        this.abertaEm = LocalDateTime.now();

    }

    public void iniciar() {

        validarNaoFinalizada();

        if (status != StatusManutencao.ABERTA) {
            throw new OperacaoManutencaoInvalidaException(
                    "Somente manutenções abertas podem ser iniciadas."
            );
        }

        status = StatusManutencao.EM_EXECUCAO;
        iniciadaEm = LocalDateTime.now();

    }

    public void aguardarPeca() {

        validarNaoFinalizada();

        if (status != StatusManutencao.EM_EXECUCAO) {
            throw new OperacaoManutencaoInvalidaException(
                    "Somente manutenções em execução podem aguardar peça."
            );
        }

        status = StatusManutencao.AGUARDANDO_PECA;

    }

    public void concluir() {

        validarNaoFinalizada();

        if (
                status != StatusManutencao.EM_EXECUCAO &&
                        status != StatusManutencao.AGUARDANDO_PECA
        ) {

            throw new OperacaoManutencaoInvalidaException(
                    "Somente manutenções em execução podem ser concluídas."
            );

        }

        boolean possuiPendencia =
                checklists.stream()
                        .anyMatch(item ->
                                !item.isConcluido()
                        );

        if (possuiPendencia) {
            throw new ChecklistPendenteException();
        }

        status = StatusManutencao.CONCLUIDA;

        concluidaEm = LocalDateTime.now();

    }

    public void cancelar() {

        validarNaoFinalizada();

        status = StatusManutencao.CANCELADA;

        canceladaEm = LocalDateTime.now();

    }

    public void adicionarChecklist(
            String descricao
    ) {

        validarNaoFinalizada();

        checklists.add(
                new ChecklistManutencao(
                        UUID.randomUUID(),
                        descricao
                )
        );

    }

    public void concluirChecklist(
            UUID checklistId
    ) {

        ChecklistManutencao checklist =
                checklists.stream()
                        .filter(item ->
                                item.getId()
                                        .equals(checklistId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Checklist não encontrado."
                                )
                        );

        checklist.concluir();

    }

    private void validarNaoFinalizada() {

        if (status == StatusManutencao.CONCLUIDA) {
            throw new OperacaoManutencaoInvalidaException(
                    "Manutenção concluída não pode ser alterada."
            );
        }

        if (status == StatusManutencao.CANCELADA) {
            throw new OperacaoManutencaoInvalidaException(
                    "Manutenção cancelada não pode ser alterada."
            );
        }

    }


}