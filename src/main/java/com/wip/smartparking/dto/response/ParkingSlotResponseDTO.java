package com.wip.smartparking.dto.response;

import com.wip.smartparking.enums.SlotStatus;
import com.wip.smartparking.enums.SlotType;

public class ParkingSlotResponseDTO {

    private Long slotId;

    private String slotNumber;

    private SlotType slotType;

    private Integer floorNo;

    private SlotStatus status;

	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}

	public Integer getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(Integer floorNo) {
		this.floorNo = floorNo;
	}

	public SlotStatus getStatus() {
		return status;
	}

	public void setStatus(SlotStatus status) {
		this.status = status;
	}

    // Generate Getters & Setters
    
}