package com.wip.smartparking.mapper;

import com.wip.smartparking.dto.request.ParkingRecordRequestDTO;
import com.wip.smartparking.dto.response.ParkingRecordResponseDTO;
import com.wip.smartparking.entity.ParkingRecord;
import com.wip.smartparking.entity.ParkingSlot;
import com.wip.smartparking.entity.Vehicle;

public class ParkingRecordMapper {

    public static ParkingRecord toEntity(ParkingRecordRequestDTO dto) {

        ParkingRecord record = new ParkingRecord();

        record.setEntryTime(dto.getEntryTime());
        record.setExitTime(dto.getExitTime());
        record.setDuration(dto.getDuration());

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(dto.getVehicleId());
        record.setVehicle(vehicle);

        ParkingSlot slot = new ParkingSlot();
        slot.setSlotId(dto.getSlotId());
        record.setParkingSlot(slot);

        return record;
    }

    public static ParkingRecordResponseDTO toResponseDTO(ParkingRecord record) {

        ParkingRecordResponseDTO dto = new ParkingRecordResponseDTO();

        dto.setRecordId(record.getRecordId());
        dto.setEntryTime(record.getEntryTime());
        dto.setExitTime(record.getExitTime());
        dto.setDuration(record.getDuration());

        if (record.getVehicle() != null) {
            dto.setVehicleId(record.getVehicle().getVehicleId());
        }

        if (record.getParkingSlot() != null) {
            dto.setSlotId(record.getParkingSlot().getSlotId());
        }

        return dto;
    }
}