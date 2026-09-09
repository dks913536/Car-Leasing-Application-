package com.carlease.customer.dto;

import com.carlease.customer.entity.EmploymentType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CustomerRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Phone is required")
        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Phone must contain exactly 10 digits"
        )
        String phone,

        @NotBlank(message = "Address is required")
        String address,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "Pincode is required")
        @Pattern(
                regexp = "^[0-9]{6}$",
                message = "Pincode must contain exactly 6 digits"
        )
        String pincode,

        @NotNull(message = "Employment type is required")
        EmploymentType employmentType,

        @NotNull(message = "Monthly income is required")
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal monthlyIncome,

        @NotNull(message = "Credit score is required")
        @Min(value = 300, message = "Credit score must be at least 300")
        @Max(value = 900, message = "Credit score must not exceed 900")
        Integer creditScore
) {
}
