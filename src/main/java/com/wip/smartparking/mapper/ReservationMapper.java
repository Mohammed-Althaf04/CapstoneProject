package com.wip.smartparking.mapper;

import com.wip.smartparking.dto.request.ReservationRequestDTO;
import com.wip.smartparking.dto.response.ReservationResponseDTO;
import com.wip.smartparking.entity.Reservation;

public class ReservationMapper {

    public static Reservation toEntity(ReservationRequestDTO dto) {

        Reservation reservation = new Reservation();

        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());

        return reservation;
    }

    public static ReservationResponseDTO toResponseDTO(
            Reservation reservation) {

        ReservationResponseDTO dto =
                new ReservationResponseDTO();

        dto.setReservationId(
                reservation.getReservationId());

        dto.setStartTime(
                reservation.getStartTime());

        dto.setEndTime(
                reservation.getEndTime());

        dto.setStatus(
                reservation.getStatus());

        if(reservation.getUser() != null) {
            dto.setUserId(
                    reservation.getUser().getUserId());
        }

        if(reservation.getVehicle() != null) {
            dto.setVehicleId(
                    reservation.getVehicle().getVehicleId());
        }

        if(reservation.getParkingSlot() != null) {
            dto.setSlotId(
                    reservation.getParkingSlot().getSlotId());
        }

        return dto;
    }
}