package com.wip.smartparking.service;

import com.wip.smartparking.entity.ParkingSlot;
import com.wip.smartparking.entity.Reservation;
import com.wip.smartparking.enums.ReservationStatus;
import com.wip.smartparking.enums.SlotStatus;
import com.wip.smartparking.repository.ParkingSlotRepository;
import com.wip.smartparking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ReservationScheduler {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

//    @Scheduled(cron = "0 * * * * *")
    public void checkReservations() {

        LocalDateTime now = LocalDateTime.now();
        List<ReservationStatus> activeStatuses =
                Arrays.asList(ReservationStatus.CONFIRMED);

        List<Reservation> reservations =
                reservationRepository.findByStatusIn(activeStatuses);

        for (Reservation reservation : reservations) {

            if (reservation.getParkingSlot() == null) continue;
            if (reservation.getEndTime() == null || reservation.getStartTime() == null) continue;

            ParkingSlot slot = reservation.getParkingSlot();

            // EXPIRED RESERVATION
            if (now.isAfter(reservation.getEndTime())) {

                reservation.setStatus(ReservationStatus.COMPLETED);
                reservationRepository.save(reservation);

                System.out.println("Reservation ID " +
                        reservation.getReservationId() + " marked COMPLETED.");

                // check if any other active reservation exists
                boolean hasOtherActiveNow =
                        reservationRepository
                                .findOverlappingReservations(
                                        slot.getSlotId(),
                                        now,
                                        now,
                                        activeStatuses
                                )
                                .stream()
                                .anyMatch(r ->
                                        !r.getReservationId().equals(reservation.getReservationId())
                                );

                if (!hasOtherActiveNow) {
                    slot.setStatus(SlotStatus.AVAILABLE);
                    parkingSlotRepository.save(slot);
                }
            }

            // ACTIVE RESERVATION WINDOW
            else if (!now.isBefore(reservation.getStartTime())) {

                if (slot.getStatus() == SlotStatus.AVAILABLE) {
                    slot.setStatus(SlotStatus.RESERVED);
                    parkingSlotRepository.save(slot);
                }
            }
        }
    }
}
