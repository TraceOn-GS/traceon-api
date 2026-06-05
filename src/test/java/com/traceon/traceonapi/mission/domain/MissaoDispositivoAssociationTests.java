package com.traceon.traceonapi.mission.domain;

import com.traceon.traceonapi.mission.domain.entity.Missao;
import com.traceon.traceonapi.mission.domain.enums.PrioridadeMissao;
import com.traceon.traceonapi.mission.domain.exception.DispositivoJaAssociadoException;
import com.traceon.traceonapi.mission.domain.exception.DispositivoNaoAssociadoException;
import com.traceon.traceonapi.mission.domain.exception.OperacaoMissaoInvalidaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MissaoDispositivoAssociationTests {

    @Test
    void associaEDesassociaDispositivoComSucesso() {
        Missao missao = novaMissao();
        UUID dispositivoId = UUID.randomUUID();

        missao.associarDispositivo(dispositivoId);
        assertEquals(Set.of(dispositivoId), missao.getDispositivosAssociados());

        missao.desassociarDispositivo(dispositivoId);
        assertEquals(Set.of(), missao.getDispositivosAssociados());
    }

    @Test
    void naoPermiteAssociarDispositivoDuplicado() {
        Missao missao = novaMissao();
        UUID dispositivoId = UUID.randomUUID();

        missao.associarDispositivo(dispositivoId);

        assertThrows(DispositivoJaAssociadoException.class, () -> missao.associarDispositivo(dispositivoId));
    }

    @Test
    void naoPermiteDesassociarDispositivoInexistente() {
        Missao missao = novaMissao();
        UUID dispositivoId = UUID.randomUUID();

        assertThrows(DispositivoNaoAssociadoException.class, () -> missao.desassociarDispositivo(dispositivoId));
    }

    @Test
    void naoPermiteAlteracaoQuandoMissaoFinalizada() {
        Missao missao = novaMissao();
        missao.iniciar();
        missao.finalizar();

        assertThrows(OperacaoMissaoInvalidaException.class, () -> missao.associarDispositivo(UUID.randomUUID()));
    }

    private Missao novaMissao() {
        return new Missao(
                UUID.randomUUID(),
                "MSN-ASSOC",
                "Missao Associacao",
                "Gerenciar dispositivos",
                "Descricao da missao",
                PrioridadeMissao.ALTA,
                LocalDateTime.now().plusDays(10)
        );
    }
}

