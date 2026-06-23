package com.wip.smartparking.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
/**
 * Entity model representing a persistent ParkingRecord database table mapped via JPA.
 *
 * @author Naveen Muthu
 */

@Entity
@Table(name = "parking_records")

public class ParkingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double duration;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private ParkingSlot parkingSlot;
    
	public ParkingRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ParkingRecord(Long recordId, LocalDateTime entryTime, LocalDateTime exitTime, Double duration,
			Vehicle vehicle, ParkingSlot parkingSlot) {
		super();
		this.recordId = recordId;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.duration = duration;
		this.vehicle = vehicle;
		this.parkingSlot = parkingSlot;
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ParkingSlot getParkingSlot() {
		return parkingSlot;
	}

	public void setParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlot = parkingSlot;
	}

	@Override
	public String toString() {
		return "ParkingRecord [recordId=" + recordId + ", entryTime=" + entryTime + ", exitTime=" + exitTime
				+ ", duration=" + duration + ", vehicle=" + vehicle + ", parkingSlot=" + parkingSlot + "]";
	}
	
}