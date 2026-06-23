package com.wip.smartparking.dto.response;

import java.time.LocalDateTime;

import com.wip.smartparking.enums.ReservationStatus;
/**
 * Data Transfer Object (DTO) representing a serialized response payload for Reservation data.
 *
 * @author Naveen Muthu
 */

public class ReservationResponseDTO {

    private Long reservationId;
    private Long userId;
    private Long vehicleId;
    private Long slotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    
	public ReservationResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReservationResponseDTO(Long reservationId, Long userId, Long vehicleId, Long slotId, LocalDateTime startTime, LocalDateTime endTime, ReservationStatus status) {
		super();
		this.reservationId = reservationId;
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.slotId = slotId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
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
		return "ReservationResponseDTO [reservationId=" + reservationId + ", userId=" + userId + ", vehicleId="
				+ vehicleId + ", slotId=" + slotId + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + "]";
	} 
    
}