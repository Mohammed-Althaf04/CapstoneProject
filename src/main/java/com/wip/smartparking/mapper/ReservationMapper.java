package com.wip.smartparking.mapper;

import com.wip.smartparking.dto.request.ReservationRequestDTO;
import com.wip.smartparking.dto.response.ReservationResponseDTO;
import com.wip.smartparking.entity.Reservation;
import com.wip.smartparking.entity.User;
import com.wip.smartparking.entity.Vehicle;
import com.wip.smartparking.entity.ParkingSlot;
import com.wip.smartparking.enums.ReservationStatus;

public class ReservationMapper {

    public static Reservation toEntity(ReservationRequestDTO dto) {

        Reservation reservation = new Reservation();
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());

        if (dto.getUserId() != null) {
            User user = new User();
            user.setUserId(dto.getUserId());
            reservation.setUser(user);
        }

        if (dto.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(dto.getVehicleId());
            reservation.setVehicle(vehicle);
        }

        if (dto.getSlotId() != null) {
            ParkingSlot slot = new ParkingSlot();
            slot.setSlotId(dto.getSlotId());
            reservation.setParkingSlot(slot);
        }

        reservation.setStatus(
                dto.getStatus() != null
                        ? dto.getStatus()
                        : ReservationStatus.PENDING
        );

        return reservation;
    }

    public static ReservationResponseDTO toResponseDTO(
            Reservation reservation) {

        ReservationResponseDTO dto =
                new ReservationResponseDTO();

        dto.setReservationId(reservation.getReservationId());
        dto.setStartTime(reservation.getStartTime());
        dto.setEndTime(reservation.getEndTime());
        dto.setStatus(reservation.getStatus());

        if (reservation.getUser() != null) {
            dto.setUserId(reservation.getUser().getUserId());
        }

        if (reservation.getVehicle() != null) {
            dto.setVehicleId(reservation.getVehicle().getVehicleId());
        }

        if (reservation.getParkingSlot() != null) {
            dto.setSlotId(reservation.getParkingSlot().getSlotId());
        }

        return dto;
    }
}