package com.traceon.traceonapi.telemetry.domain.valueobject;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Localizacao {

    private final Double latitude;
    private final Double longitude;
    private final Double altitude;

    public Localizacao(
            Double latitude,
            Double longitude,
            Double altitude
    ) {

        this.latitude = Objects.requireNonNull(
                latitude,
                "Latitude obrigatória"
        );

        this.longitude = Objects.requireNonNull(
                longitude,
                "Longitude obrigatória"
        );

        this.altitude = Objects.requireNonNull(
                altitude,
                "Altitude obrigatória"
        );

    }

}