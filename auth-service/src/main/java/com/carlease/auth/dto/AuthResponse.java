package com.carlease.auth.dto;

public record AuthResponse(
        String token,
        String tokenType,
        Long userId,
        String name,
        String email,
        String role
) {
    public AuthResponse(String token, Long userId, String name, String email, String role) {
        this(token, "Bearer", userId, name, email, role);
    }
}
