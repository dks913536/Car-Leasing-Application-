package com.carlease.vehicle.dto;

import com.carlease.vehicle.entity.FuelType;
import com.carlease.vehicle.entity.TransmissionType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class VehicleRequest {

    @NotBlank(message = "Brand is required")
    @Size(max = 100, message = "Brand must not exceed 100 characters")
    private String brand;

    @NotBlank(message = "Model is required")
    @Size(max = 100, message = "Model must not exceed 100 characters")
    private String model;

    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    @Max(value = 2100, message = "Year must be less than or equal to 2100")
    private Integer year;

    @NotBlank(message = "Registration number is required")
    @Size(max = 30, message = "Registration number must not exceed 30 characters")
    private String registrationNumber;

    @NotNull(message = "Monthly lease price is required")
    @DecimalMin(value = "0.01", message = "Monthly lease price must be greater than zero")
    private BigDecimal pricePerMonth;

    @NotNull(message = "Fuel type is required")
    private FuelType fuelType;

    @NotNull(message = "Transmission type is required")
    private TransmissionType transmissionType;

    @Size(max = 50, message = "Color must not exceed 50 characters")
    private String color;

    @Min(value = 1, message = "Seating capacity must be at least 1")
    private Integer seatingCapacity;

    @Min(value = 0, message = "Mileage cannot be negative")
    private Integer mileage;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    public VehicleRequest() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public BigDecimal getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(BigDecimal pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
