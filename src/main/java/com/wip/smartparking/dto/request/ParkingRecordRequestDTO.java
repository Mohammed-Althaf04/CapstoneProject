package com.wip.smartparking.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
/**
 * Data Transfer Object (DTO) representing a request body for ParkingRecord creation or updates.
 *
 * @author Naveen Muthu
 */

public class ParkingRecordRequestDTO {

    @NotNull(message = "Entry time is required")
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private Double duration;

    @NotNull(message = "Vehicle Id is required")
    private Long vehicleId;

    @NotNull(message = "Slot Id is required")
    private Long slotId;

    public ParkingRecordRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingRecordRequestDTO(LocalDateTime entryTime,LocalDateTime exitTime, Double duration,Long vehicleId,
			Long slotId) {
		super();
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.duration = duration;
		this.vehicleId = vehicleId;
		this.slotId = slotId;
	}

	public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

	@Override
	public String toString() {
		return "ParkingRecordRequestDTO [entryTime=" + entryTime + ", exitTime=" + exitTime + ", duration=" + duration
				+ ", vehicleId=" + vehicleId + ", slotId=" + slotId + "]";
	}
    
}