package com.wip.smartparking.dto.response;

import java.time.LocalDateTime;

public class ParkingRecordResponseDTO {

    private Long recordId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double duration;
    private Long vehicleId;
    private Long slotId;
    
    public ParkingRecordResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public ParkingRecordResponseDTO(Long recordId, LocalDateTime entryTime, LocalDateTime exitTime, Double duration,
			Long vehicleId, Long slotId) {
		super();
		this.recordId = recordId;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.duration = duration;
		this.vehicleId = vehicleId;
		this.slotId = slotId;
	}

	public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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
		return "ParkingRecordResponseDTO [recordId=" + recordId + ", entryTime=" + entryTime + ", exitTime=" + exitTime
				+ ", duration=" + duration + ", vehicleId=" + vehicleId + ", slotId=" + slotId + "]";
	}
    
}