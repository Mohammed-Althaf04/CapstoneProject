package com.wip.smartparking.dto.request;

import java.time.LocalDateTime;

import com.wip.smartparking.enums.ReservationStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class ReservationRequestDTO {

	@NotNull(message = "Status is required")
	private ReservationStatus status;
	
    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Vehicle Id is required")
    private Long vehicleId;

    @NotNull(message = "Slot Id is required")
    private Long slotId;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;
    
	public ReservationRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReservationRequestDTO(ReservationStatus status, Long userId, Long vehicleId, Long slotId, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.status = status;
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.slotId = slotId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	public ReservationStatus getStatus() {
	    return status;
	}

	public void setStatus(ReservationStatus status) {
	    this.status = status;
	}

	@Override
	public String toString() {
		return "ReservationRequestDTO [status=" + status + ", userId=" + userId + ", vehicleId=" + vehicleId
				+ ", slotId=" + slotId + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
    
}