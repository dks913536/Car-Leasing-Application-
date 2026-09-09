package com.carlease.customer.dto;

import com.carlease.customer.entity.EmploymentType;

import java.math.BigDecimal;
import java.time.Instant;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String phone,
        String address,
        String city,
        String state,
        String pincode,
        EmploymentType employmentType,
        BigDecimal monthlyIncome,
        Integer creditScore,
        Instant createdAt,
        Instant updatedAt
) {
}
