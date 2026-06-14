package com.wip.smartparking.entity;

import com.wip.smartparking.enums.SlotStatus;

import com.wip.smartparking.enums.SlotType;

import jakarta.persistence.*;


@Entity
@Table(name = "parking_slots")

public class ParkingSlot {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

    private String slotNumber;

    @Enumerated(EnumType.STRING)
    private SlotType slotType;

    private Integer floorNo;

    @Enumerated(EnumType.STRING)
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

	
}