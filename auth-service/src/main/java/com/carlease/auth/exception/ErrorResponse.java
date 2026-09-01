package com.carlease.auth.exception;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String message,
        Map<String, String> errors
) {
    public ErrorResponse(int status, String message) {
        this(Instant.now(), status, message, null);
    }

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this(Instant.now(), status, message, errors);
    }
}
