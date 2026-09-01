package com.carlease.auth.dto;

import com.carlease.auth.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name should not exceed 100 characters")
        String name,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is required")
        @Size(max = 150, message = "Email should not exceed 150 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password should be between 6 and 100 characters")
        String password,

        @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone should contain 10 to 15 digits")
        String phone,

        @NotNull(message = "Role is required")
        Role role
) {
}
