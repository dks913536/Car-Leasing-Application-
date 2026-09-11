package com.carlease.quotation.dto;

import com.carlease.quotation.entity.QuotationStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record QuotationResponse(
        Long id,
        Long customerId,
        Long vehicleId,
        String vehicleBrand,
        String vehicleModel,
        String customerName,
        BigDecimal monthlyLeaseAmount,
        Integer leaseTenureMonths,
        BigDecimal downPayment,
        BigDecimal totalLeaseAmount,
        QuotationStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
