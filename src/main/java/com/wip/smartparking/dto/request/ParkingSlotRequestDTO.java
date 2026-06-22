package com.wip.smartparking.dto.request;

import com.wip.smartparking.enums.SlotStatus;
import com.wip.smartparking.enums.SlotType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ParkingSlotRequestDTO {

    @NotBlank(message = "Slot number is required")
    private String slotNumber;

    @NotNull(message = "Slot type is required")
    private SlotType slotType;

    @NotNull(message = "Floor number is required")
    @Positive(message = "Floor number must be greater than zero")
    private Integer floorNo;

    @NotNull(message = "Slot status is required")
    private SlotStatus status;

	public ParkingSlotRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ParkingSlotRequestDTO(String slotNumber,SlotType slotType,Integer floorNo,SlotStatus status) {
		super();
		this.slotNumber = slotNumber;
		this.slotType = slotType;
		this.floorNo = floorNo;
		this.status = status;
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

	@Override
	public String toString() {
		return "ParkingSlotRequestDTO [slotNumber=" + slotNumber + ", slotType=" + slotType + ", floorNo=" + floorNo
				+ ", status=" + status + "]";
	}
    
}