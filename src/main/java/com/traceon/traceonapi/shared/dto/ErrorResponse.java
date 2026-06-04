package com.traceon.traceonapi.shared.dto;

public record ErrorResponse(

        String message,

        int status

) {
}