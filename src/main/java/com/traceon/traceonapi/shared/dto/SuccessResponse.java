package com.traceon.traceonapi.shared.dto;

public record SuccessResponse<T>(

        String message,

        int status,

        T data

) {
}