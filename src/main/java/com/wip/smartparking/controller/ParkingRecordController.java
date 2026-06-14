package com.wip.smartparking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wip.smartparking.dto.request.ParkingRecordRequestDTO;
import com.wip.smartparking.dto.response.ParkingRecordResponseDTO;
import com.wip.smartparking.entity.ParkingRecord;
import com.wip.smartparking.mapper.ParkingRecordMapper;
import com.wip.smartparking.service.ParkingRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/parking-records")
@Validated
public class ParkingRecordController {

    @Autowired
    private ParkingRecordService parkingRecordService;

    @PostMapping
    public ParkingRecordResponseDTO saveParkingRecord(
            @Valid @RequestBody ParkingRecordRequestDTO dto) {

        ParkingRecord record =
                ParkingRecordMapper.toEntity(dto);

        ParkingRecord savedRecord =
                parkingRecordService.saveRecord(record);

        return ParkingRecordMapper.toResponseDTO(savedRecord);
    }

    @GetMapping("/{id}")
    public ParkingRecordResponseDTO getParkingRecordById(
            @PathVariable Long id) {

        ParkingRecord record =
                parkingRecordService.getRecordById(id);

        return ParkingRecordMapper.toResponseDTO(record);
    }

    @GetMapping
    public List<ParkingRecordResponseDTO> getAllParkingRecords() {

        return parkingRecordService.getAllRecords()
                .stream()
                .map(ParkingRecordMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public String deleteParkingRecord(
            @PathVariable Long id) {

        parkingRecordService.deleteRecord(id);

        return "Parking Record deleted successfully";
    }
}