package com.wip.smartparking.mapper;

import com.wip.smartparking.dto.request.ParkingSlotRequestDTO;
import com.wip.smartparking.dto.response.ParkingSlotResponseDTO;
import com.wip.smartparking.entity.ParkingSlot;

public class ParkingSlotMapper {

    public static ParkingSlot toEntity(ParkingSlotRequestDTO dto) {

        ParkingSlot slot = new ParkingSlot();

        slot.setSlotNumber(dto.getSlotNumber());
        slot.setSlotType(dto.getSlotType());
        slot.setFloorNo(dto.getFloorNo());
        slot.setStatus(dto.getStatus());

        return slot;
    }

    public static ParkingSlotResponseDTO toResponseDTO(ParkingSlot slot) {

        ParkingSlotResponseDTO dto = new ParkingSlotResponseDTO();

        dto.setSlotId(slot.getSlotId());
        dto.setSlotNumber(slot.getSlotNumber());
        dto.setSlotType(slot.getSlotType());
        dto.setFloorNo(slot.getFloorNo());
        dto.setStatus(slot.getStatus());

        return dto;
    }
}