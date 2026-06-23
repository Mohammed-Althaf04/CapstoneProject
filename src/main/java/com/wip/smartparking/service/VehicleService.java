package com.wip.smartparking.service;

import java.util.List;
import com.wip.smartparking.entity.Vehicle;
/**
 * Service interface defining the business contract and operations for Vehicle management.
 *
 * @author Naveen Muthu
 */

public interface VehicleService {

    Vehicle saveVehicle(Vehicle vehicle, Long userId);
    Vehicle getVehicleById(Long id);
    List<Vehicle> getAllVehicles();
    void deleteVehicle(Long id);
}