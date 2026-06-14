package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}