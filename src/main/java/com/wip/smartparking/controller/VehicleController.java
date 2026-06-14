package com.wip.smartparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wip.smartparking.dto.request.VehicleRequestDTO;
import com.wip.smartparking.dto.response.VehicleResponseDTO;
import com.wip.smartparking.entity.Vehicle;
import com.wip.smartparking.mapper.VehicleMapper;
import com.wip.smartparking.service.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public VehicleResponseDTO saveVehicle(
            @Valid @RequestBody VehicleRequestDTO dto) {

        Vehicle vehicle = VehicleMapper.toEntity(dto);

        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);

        return VehicleMapper.toResponseDTO(savedVehicle);
    }

    @GetMapping("/{id}")
    public VehicleResponseDTO getVehicleById(@PathVariable Long id) {

        Vehicle vehicle = vehicleService.getVehicleById(id);

        return VehicleMapper.toResponseDTO(vehicle);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id) {

        vehicleService.deleteVehicle(id);

        return "Vehicle deleted successfully";
    }
}