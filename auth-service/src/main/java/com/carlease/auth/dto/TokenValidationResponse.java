package com.carlease.auth.dto;

public record TokenValidationResponse(
        boolean valid,
        Long userId,
        String email,
        String role
) {
}
