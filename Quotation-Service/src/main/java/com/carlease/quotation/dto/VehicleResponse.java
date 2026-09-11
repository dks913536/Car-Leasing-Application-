package com.carlease.quotation.dto;

import java.math.BigDecimal;

public record VehicleResponse(
        Long id,
        String brand,
        String model,
        Integer year,
        String registrationNumber,
        BigDecimal pricePerMonth,
        String fuelType,
        String transmissionType,
        String status,
        String color,
        Integer seatingCapacity,
        Double mileage,
        String description
) {
}
