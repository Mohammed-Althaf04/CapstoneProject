package com.wip.smartparking.dto.response;

import com.wip.smartparking.enums.SlotStatus;
import com.wip.smartparking.enums.SlotType;

public class ParkingSlotResponseDTO {

    private Long slotId;
    private String slotNumber;
    private SlotType slotType;
    private Integer floorNo;
    private SlotStatus status;
    
	public ParkingSlotResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ParkingSlotResponseDTO(Long slotId, String slotNumber, SlotType slotType, Integer floorNo,
			SlotStatus status) {
		super();
		this.slotId = slotId;
		this.slotNumber = slotNumber;
		this.slotType = slotType;
		this.floorNo = floorNo;
		this.status = status;
	}

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

	@Override
	public String toString() {
		return "ParkingSlotResponseDTO [slotId=" + slotId + ", slotNumber=" + slotNumber + ", slotType=" + slotType
				+ ", floorNo=" + floorNo + ", status=" + status + "]";
	}
    
}