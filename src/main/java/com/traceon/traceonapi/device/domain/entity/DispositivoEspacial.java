package com.traceon.traceonapi.device.domain.entity;

import com.traceon.traceonapi.device.domain.enums.StatusDispositivo;
import com.traceon.traceonapi.device.domain.exception.DispositivoDesativadoException;
import com.traceon.traceonapi.device.dto.CreateDispositivoRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DispositivoEspacial {

    private UUID id;
    private String codigoSerial;
    private String modelo;
    private Double energiaAtual;
    private StatusDispositivo status;

    public DispositivoEspacial(
            UUID id,
            String codigoSerial,
            String modelo,
            Double energiaAtual
    ) {

        if (energiaAtual < 0 || energiaAtual > 100) {
            throw new IllegalArgumentException(
                    "Energia deve estar entre 0 e 100"
            );
        }

        this.id = id;
        this.codigoSerial = codigoSerial;
        this.modelo = modelo;
        this.energiaAtual = energiaAtual;
        this.status = StatusDispositivo.OPERACIONAL;

    }

    public void ativar() {
        this.status = StatusDispositivo.OPERACIONAL;

    }

    public void desativar() {

        this.status = StatusDispositivo.DESATIVADO;

    }

    public void colocarEmManutencao() {

        if (status == StatusDispositivo.DESATIVADO) {
            throw new DispositivoDesativadoException(

            );
        }

        this.status = StatusDispositivo.MANUTENCAO;

    }

    public void atualizarDados(
            String codigoSerial,
            String modelo,
            Double energiaAtual
    ) {

        if (energiaAtual < 0 || energiaAtual > 100) {
            throw new IllegalArgumentException(
                    "Energia deve estar entre 0 e 100"
            );
        }

        this.codigoSerial = codigoSerial;
        this.modelo = modelo;
        this.energiaAtual = energiaAtual;

    }

}