package com.carlease.auth.dto;

import java.time.Instant;

public record UserProfileResponse(
        Long id,
        String name,
        String email,
        String phone,
        String role,
        Instant createdAt
) {
}
