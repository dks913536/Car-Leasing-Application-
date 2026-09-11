package com.carlease.corporate.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CorporateRequest(

        @NotBlank(message = "Company name is required")
        @Size(max = 150)
        String companyName,

        @NotBlank(message = "Contact person is required")
        @Size(max = 100)
        String contactPerson,

        @NotBlank(message = "Contact email is required")
        @Email(message = "Invalid contact email")
        String contactEmail,

        @NotBlank(message = "Contact phone is required")
        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Phone must contain exactly 10 digits"
        )
        String contactPhone,

        @NotBlank(message = "GST number is required")
        @Pattern(
                regexp = "^[0-9A-Z]{15}$",
                message = "GST number must contain exactly 15 characters"
        )
        String gstNumber,

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

        @NotNull(message = "Fleet size is required")
        @Min(value = 1, message = "Fleet size must be at least 1")
        Integer fleetSize,

        @NotNull(message = "Annual turnover is required")
        @DecimalMin(
                value = "0.0",
                inclusive = false,
                message = "Annual turnover must be greater than 0"
        )
        BigDecimal annualTurnover
) {
}
