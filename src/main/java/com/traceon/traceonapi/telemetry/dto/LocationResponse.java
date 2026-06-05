package com.traceon.traceonapi.telemetry.dto;

public record LocationResponse(

        Double latitude,

        Double longitude,

        Double altitude

) {
}