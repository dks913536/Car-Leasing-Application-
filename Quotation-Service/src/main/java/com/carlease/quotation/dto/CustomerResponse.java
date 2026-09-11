package com.carlease.quotation.dto;

import java.math.BigDecimal;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String phone,
        String address,
        String city,
        String state,
        String pincode,
        String employmentType,
        BigDecimal monthlyIncome,
        Integer creditScore
) {
}
