package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}