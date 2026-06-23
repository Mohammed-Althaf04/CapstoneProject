package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.wip.smartparking.entity.Reservation;
import com.wip.smartparking.enums.ReservationStatus;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Spring Data JPA repository interface providing database access methods for Reservation entities.
 *
 * @author Naveen Muthu
 */

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.parkingSlot.slotId = :slotId " +
           "AND r.status IN :statuses " +
           "AND r.startTime < :endTime AND r.endTime > :startTime")
    List<Reservation> findOverlappingReservations(
            @Param("slotId") Long slotId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("statuses") List<ReservationStatus> statuses);

    List<Reservation> findByStatusIn(List<ReservationStatus> statuses);
}