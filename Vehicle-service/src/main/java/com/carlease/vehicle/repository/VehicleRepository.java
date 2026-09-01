package com.carlease.vehicle.repository;

import com.carlease.vehicle.entity.Vehicle;
import com.carlease.vehicle.entity.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);

    boolean existsByRegistrationNumber(String registrationNumber);

    boolean existsByRegistrationNumberAndIdNot(
            String registrationNumber,
            Long id
    );

    List<Vehicle> findByStatus(VehicleStatus status);

    List<Vehicle> findByBrandIgnoreCase(String brand);
}
