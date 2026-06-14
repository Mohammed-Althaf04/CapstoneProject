package com.wip.smartparking.mapper;

import com.wip.smartparking.dto.request.VehicleRequestDTO;
import com.wip.smartparking.dto.response.VehicleResponseDTO;
import com.wip.smartparking.entity.Vehicle;

public class VehicleMapper {

    public static Vehicle toEntity(VehicleRequestDTO dto) {

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleNumber(dto.getVehicleNumber());
        vehicle.setVehicleType(dto.getVehicleType());

        return vehicle;
    }

    public static VehicleResponseDTO toResponseDTO(Vehicle vehicle) {

        VehicleResponseDTO dto = new VehicleResponseDTO();

        dto.setVehicleId(vehicle.getVehicleId());
        dto.setVehicleNumber(vehicle.getVehicleNumber());
        dto.setVehicleType(vehicle.getVehicleType());

        if(vehicle.getUser() != null) {
            dto.setUserId(vehicle.getUser().getUserId());
        }

        return dto;
    }
}