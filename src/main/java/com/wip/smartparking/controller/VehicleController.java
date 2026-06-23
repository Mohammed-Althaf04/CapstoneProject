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
/**
 * REST Controller exposing API endpoints for CRUD and business operations on Vehicle resources.
 *
 * @author Naveen Muthu
 */

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public VehicleResponseDTO saveVehicle(@Valid @RequestBody VehicleRequestDTO dto) {
        Vehicle vehicle = VehicleMapper.toEntity(dto);
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle, dto.getUserId());
        return VehicleMapper.toResponseDTO(savedVehicle);
    }

    @GetMapping("getAVehicle/{id}")
    public VehicleResponseDTO getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return VehicleMapper.toResponseDTO(vehicle);
    }

    @GetMapping("/listAll")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @DeleteMapping("delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "Vehicle deleted successfully";
    }
}