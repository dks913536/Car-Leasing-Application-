package com.carlease.corporate.dto;

import com.carlease.corporate.entity.CorporateStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record CorporateResponse(
        Long id,
        String companyName,
        String contactPerson,
        String contactEmail,
        String contactPhone,
        String gstNumber,
        String address,
        String city,
        String state,
        String pincode,
        Integer fleetSize,
        BigDecimal annualTurnover,
        CorporateStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
