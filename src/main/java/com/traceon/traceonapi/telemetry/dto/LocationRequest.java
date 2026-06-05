package com.traceon.traceonapi.telemetry.dto;

import jakarta.validation.constraints.NotNull;

public record LocationRequest(

        @NotNull(
                message = "Latitude obrigatória"
        )
        Double latitude,

        @NotNull(
                message = "Longitude obrigatória"
        )
        Double longitude,

        @NotNull(
                message = "Altitude obrigatória"
        )
        Double altitude

) {
}