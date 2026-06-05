package com.traceon.traceonapi.telemetry.domain.service;

import com.traceon.traceonapi.alert.domain.entity.Alerta;
import com.traceon.traceonapi.alert.domain.enums.OrigemAlerta;
import com.traceon.traceonapi.alert.domain.enums.SeveridadeAlerta;
import com.traceon.traceonapi.alert.domain.enums.TipoAlerta;
import com.traceon.traceonapi.telemetry.domain.entity.Telemetria;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TelemetryAnalysisService {

    private static final Double LIMITE_RADIACAO = 500.0;

    public List<Alerta> analisar(
            Telemetria telemetria
    ) {

        List<Alerta> alertas = new ArrayList<>();

        if (telemetria.getTemperaturaInterna() > 95) {

            alertas.add(
                    criarAlerta(
                            TipoAlerta.TEMPERATURA_CRITICA,
                            SeveridadeAlerta.CRITICA,
                            "Temperatura interna acima de 95°C",
                            telemetria
                    )
            );

        } else if (telemetria.getTemperaturaInterna() > 80) {

            alertas.add(
                    criarAlerta(
                            TipoAlerta.TEMPERATURA_ELEVADA,
                            SeveridadeAlerta.ALTA,
                            "Temperatura interna acima de 80°C",
                            telemetria
                    )
            );

        }

        if (telemetria.getNivelEnergia() < 20) {

            alertas.add(
                    criarAlerta(
                            TipoAlerta.ENERGIA_BAIXA,
                            SeveridadeAlerta.ALTA,
                            "Nível de energia abaixo de 20%",
                            telemetria
                    )
            );

        }

        if (telemetria.getQualidadeSinal() < 30) {

            alertas.add(
                    criarAlerta(
                            TipoAlerta.SINAL_FRACO,
                            SeveridadeAlerta.MEDIA,
                            "Qualidade do sinal abaixo de 30%",
                            telemetria
                    )
            );

        }

        if (telemetria.getRadiacao() > LIMITE_RADIACAO) {

            alertas.add(
                    criarAlerta(
                            TipoAlerta.RADIACAO_ELEVADA,
                            SeveridadeAlerta.CRITICA,
                            "Radiação acima do limite operacional",
                            telemetria
                    )
            );

        }

        return alertas;

    }

    private Alerta criarAlerta(
            TipoAlerta tipo,
            SeveridadeAlerta severidade,
            String descricao,
            Telemetria telemetria
    ) {

        return new Alerta(
                UUID.randomUUID(),
                gerarCodigo(),
                telemetria.getDispositivoId(),
                telemetria.getId(),
                tipo,
                severidade,
                descricao,
                OrigemAlerta.TELEMETRIA

        );

    }

    private String gerarCodigo() {

        return "ALT-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

    }

}