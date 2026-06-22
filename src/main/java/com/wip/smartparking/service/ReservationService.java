package com.wip.smartparking.service;

import java.util.List;

import com.wip.smartparking.entity.Reservation;

public interface ReservationService {

	Reservation saveReservation(Reservation reservation, Long userId, Long slotId);
    Reservation getReservationById(Long id);
    List<Reservation> getAllReservations();
    void deleteReservation(Long id);
    List<Reservation> getActiveReservationsForSlot(Long slotId);
}