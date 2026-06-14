package com.wip.smartparking.service;

import java.util.List;

import com.wip.smartparking.entity.Reservation;

public interface ReservationService {

    Reservation saveReservation(Reservation reservation);

    Reservation getReservationById(Long id);

    List<Reservation> getAllReservations();

    void deleteReservation(Long id);
}