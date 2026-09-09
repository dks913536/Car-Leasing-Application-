package com.carlease.customer.dto;

public record EligibilityResponse(
        Long customerId,
        boolean eligible,
        String reason
) {
}
