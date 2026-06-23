package com.wip.smartparking.entity;

import java.time.LocalDateTime;

import com.wip.smartparking.enums.ReservationStatus;

import jakarta.persistence.*;
/**
 * Entity model representing a persistent Reservation database table mapped via JPA.
 *
 * @author Naveen Muthu
 */


@Entity
@Table(name = "reservations")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private ParkingSlot parkingSlot;
    
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reservation(Long reservationId, LocalDateTime startTime, LocalDateTime endTime, ReservationStatus status,
			User user, Vehicle vehicle, ParkingSlot parkingSlot) {
		super();
		this.reservationId = reservationId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.user = user;
		this.vehicle = vehicle;
		this.parkingSlot = parkingSlot;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return "Reservation [reservationId=" + reservationId + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + ", user=" + user + ", vehicle=" + vehicle + ", parkingSlot=" + parkingSlot
				+ "]";
	}
    
}