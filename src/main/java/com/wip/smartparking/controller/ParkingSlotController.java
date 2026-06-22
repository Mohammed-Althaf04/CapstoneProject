package com.wip.smartparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wip.smartparking.dto.request.ParkingSlotRequestDTO;
import com.wip.smartparking.dto.response.ParkingSlotResponseDTO;
import com.wip.smartparking.entity.ParkingSlot;
import com.wip.smartparking.mapper.ParkingSlotMapper;
import com.wip.smartparking.service.ParkingSlotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/parking-slots")
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService parkingSlotService;

    @PostMapping
    public ParkingSlotResponseDTO saveSlot(@Valid @RequestBody ParkingSlotRequestDTO dto) {

        ParkingSlot slot = ParkingSlotMapper.toEntity(dto);

        ParkingSlot savedSlot = parkingSlotService.saveSlot(slot);

        return ParkingSlotMapper.toResponseDTO(savedSlot);
    }

    @GetMapping("getASlot/{id}")
    public ParkingSlotResponseDTO getSlotById(@PathVariable Long id) {

        ParkingSlot slot = parkingSlotService.getSlotById(id);

        return ParkingSlotMapper.toResponseDTO(slot);
    }

    @GetMapping("/listAll")
    public List<ParkingSlot> getAllSlots() {
        return parkingSlotService.getAllSlots();
    }

    @DeleteMapping("delete/{id}")
    public String deleteSlot(@PathVariable Long id) {

        parkingSlotService.deleteSlot(id);

        return "Slot deleted successfully";
    }
}