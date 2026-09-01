package com.carlease.vehicle.dto;

import com.carlease.vehicle.entity.FuelType;
import com.carlease.vehicle.entity.TransmissionType;
import com.carlease.vehicle.entity.VehicleStatus;

import java.math.BigDecimal;

public class VehicleResponse {

    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String registrationNumber;
    private BigDecimal pricePerMonth;
    private FuelType fuelType;
    private TransmissionType transmissionType;
    private VehicleStatus status;
    private String color;
    private Integer seatingCapacity;
    private Integer mileage;
    private String description;

    public VehicleResponse() {
    }

    public VehicleResponse(
            Long id,
            String brand,
            String model,
            Integer year,
            String registrationNumber,
            BigDecimal pricePerMonth,
            FuelType fuelType,
            TransmissionType transmissionType,
            VehicleStatus status,
            String color,
            Integer seatingCapacity,
            Integer mileage,
            String description) {

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.pricePerMonth = pricePerMonth;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.status = status;
        this.color = color;
        this.seatingCapacity = seatingCapacity;
        this.mileage = mileage;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public BigDecimal getPricePerMonth() {
        return pricePerMonth;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public String getColor() {
        return color;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public Integer getMileage() {
        return mileage;
    }

    public String getDescription() {
        return description;
    }
}
