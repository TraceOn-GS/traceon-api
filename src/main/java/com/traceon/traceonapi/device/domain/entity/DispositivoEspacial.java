package com.traceon.traceonapi.device.domain.entity;

import com.traceon.traceonapi.device.domain.enums.StatusDispositivo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispositivoEspacial {

    private Long id;

    private String codigoSerial;

    private String modelo;

    private Double energiaAtual;

    private StatusDispositivo status;

}