package com.carlease.vehicle.service;

import com.carlease.vehicle.dto.VehicleRequest;
import com.carlease.vehicle.dto.VehicleResponse;
import com.carlease.vehicle.entity.Vehicle;
import com.carlease.vehicle.entity.VehicleStatus;
import com.carlease.vehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleResponse createVehicle(VehicleRequest request) {

        if (vehicleRepository.existsByRegistrationNumber(
                request.getRegistrationNumber())) {

            throw new IllegalArgumentException(
                    "Vehicle already exists with registration number: "
                            + request.getRegistrationNumber());
        }

        Vehicle vehicle = new Vehicle();

        mapRequestToEntity(request, vehicle);

        // Newly added vehicles are available by default.
        vehicle.setStatus(VehicleStatus.AVAILABLE);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return mapToResponse(savedVehicle);
    }

    public List<VehicleResponse> getAllVehicles() {

        return vehicleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public VehicleResponse getVehicleById(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Vehicle not found with id: " + id));

        return mapToResponse(vehicle);
    }

    public List<VehicleResponse> getAvailableVehicles() {

        return vehicleRepository
                .findByStatus(VehicleStatus.AVAILABLE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<VehicleResponse> searchByBrand(String brand) {

        return vehicleRepository
                .findByBrandIgnoreCase(brand)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public VehicleResponse updateVehicle(
            Long id,
            VehicleRequest request) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Vehicle not found with id: " + id));

        if (vehicleRepository.existsByRegistrationNumberAndIdNot(
                request.getRegistrationNumber(),
                id)) {

            throw new IllegalArgumentException(
                    "Another vehicle already exists with registration number: "
                            + request.getRegistrationNumber());
        }

        mapRequestToEntity(request, vehicle);

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return mapToResponse(updatedVehicle);
    }

    public void deleteVehicle(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Vehicle not found with id: " + id));

        vehicleRepository.delete(vehicle);
    }

    public VehicleResponse updateStatus(
            Long id,
            VehicleStatus status) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Vehicle not found with id: " + id));

        vehicle.setStatus(status);

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return mapToResponse(updatedVehicle);
    }

    private void mapRequestToEntity(
            VehicleRequest request,
            Vehicle vehicle) {

        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setYear(request.getYear());
        vehicle.setRegistrationNumber(
                request.getRegistrationNumber());
        vehicle.setPricePerMonth(request.getPricePerMonth());
        vehicle.setFuelType(request.getFuelType());
        vehicle.setTransmissionType(request.getTransmissionType());
        vehicle.setColor(request.getColor());
        vehicle.setSeatingCapacity(request.getSeatingCapacity());
        vehicle.setMileage(request.getMileage());
        vehicle.setDescription(request.getDescription());
    }

    private VehicleResponse mapToResponse(Vehicle vehicle) {

        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getRegistrationNumber(),
                vehicle.getPricePerMonth(),
                vehicle.getFuelType(),
                vehicle.getTransmissionType(),
                vehicle.getStatus(),
                vehicle.getColor(),
                vehicle.getSeatingCapacity(),
                vehicle.getMileage(),
                vehicle.getDescription()
        );
    }
}
