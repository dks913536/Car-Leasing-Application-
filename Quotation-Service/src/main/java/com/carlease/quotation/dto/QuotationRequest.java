package com.carlease.quotation.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record QuotationRequest(

        @NotNull(message = "Customer ID is required")
        Long customerId,

        @NotNull(message = "Vehicle ID is required")
        Long vehicleId,

        @NotNull(message = "Lease tenure is required")
        @Min(value = 6, message = "Minimum lease tenure is 6 months")
        @Max(value = 60, message = "Maximum lease tenure is 60 months")
        Integer leaseTenureMonths,

        @NotNull(message = "Down payment is required")
        @DecimalMin(value = "0.0", inclusive = true)
        BigDecimal downPayment
) {
}
