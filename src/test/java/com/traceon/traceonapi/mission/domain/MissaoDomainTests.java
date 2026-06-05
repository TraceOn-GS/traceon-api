package com.traceon.traceonapi.mission.domain;

import com.traceon.traceonapi.mission.domain.entity.Missao;
import com.traceon.traceonapi.mission.domain.enums.PrioridadeMissao;
import com.traceon.traceonapi.mission.domain.enums.StatusMissao;
import com.traceon.traceonapi.mission.domain.exception.OperacaoMissaoInvalidaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MissaoDomainTests {

    @Test
    void iniciaEFinalizaMissaoComEventos() {
        Missao missao = novaMissao();

        missao.iniciar();
        assertEquals(StatusMissao.EM_EXECUCAO, missao.getStatus());
        assertNotNull(missao.getDataInicio());

        missao.finalizar();
        assertEquals(StatusMissao.FINALIZADA, missao.getStatus());
        assertNotNull(missao.getDataEncerramento());
    }

    @Test
    void impedeInicioQuandoFinalizada() {
        Missao missao = novaMissao();
        missao.iniciar();
        missao.finalizar();

        assertThrows(OperacaoMissaoInvalidaException.class, missao::iniciar);
    }

    private Missao novaMissao() {
        return new Missao(
                UUID.randomUUID(),
                "MSN-001",
                "Missao Teste",
                "Coletar dados",
                "Descricao da missao",
                PrioridadeMissao.MEDIA,
                LocalDateTime.now().plusDays(5)
        );
    }
}

