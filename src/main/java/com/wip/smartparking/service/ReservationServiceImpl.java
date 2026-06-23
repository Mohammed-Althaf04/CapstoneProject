package com.wip.smartparking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wip.smartparking.entity.ParkingSlot;
import com.wip.smartparking.entity.Reservation;
import com.wip.smartparking.entity.User;
import com.wip.smartparking.entity.Vehicle;
import com.wip.smartparking.enums.ReservationStatus;
import com.wip.smartparking.enums.SlotStatus;
import com.wip.smartparking.exception.ParkingSlotException;
import com.wip.smartparking.exception.ResourceNotFoundException;
import com.wip.smartparking.repository.ParkingSlotRepository;
import com.wip.smartparking.repository.ReservationRepository;
import com.wip.smartparking.repository.UserRepository;
import com.wip.smartparking.repository.VehicleRepository;
/**
 * Service implementation class containing concrete business logic and transactional operations for Reservation.
 *
 * @author Naveen Muthu
 */

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Reservation saveReservation(Reservation reservation, Long userId, Long slotId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        ParkingSlot slot = parkingSlotRepository.findById(slotId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Slot not found with id: " + slotId));

        if (reservation.getStartTime() == null || reservation.getEndTime() == null) {
            throw new ParkingSlotException("Start time and end time must be specified.");
        }

        if (!reservation.getEndTime().isAfter(reservation.getStartTime())) {
            throw new ParkingSlotException("End time must be after start time.");
        }

        // Validate time range overlap with existing bookings on the same slot
        java.util.List<ReservationStatus> activeStatuses = java.util.Arrays.asList(ReservationStatus.CONFIRMED, ReservationStatus.PENDING);
        java.util.List<Reservation> overlapping = reservationRepository.findOverlappingReservations(
                slotId, reservation.getStartTime(), reservation.getEndTime(), activeStatuses
        );

        boolean isOverlapping = overlapping.stream()
                .anyMatch(r -> reservation.getReservationId() == null || !r.getReservationId().equals(reservation.getReservationId()));

        if (isOverlapping) {
            StringBuilder sb = new StringBuilder("The parking slot is already booked during the selected time period. Conflicting Bookings: ");
            boolean first = true;
            for (Reservation r : overlapping) {
                if (reservation.getReservationId() != null && r.getReservationId().equals(reservation.getReservationId())) {
                    continue;
                }
                if (!first) {
                    sb.append(", ");
                }
                sb.append(r.getStartTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                  .append(" to ")
                  .append(r.getEndTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                first = false;
            }
            throw new ParkingSlotException(sb.toString());
        }

        LocalDateTime now = LocalDateTime.now();

        // If updating, handle old slot release on slot change
        if (reservation.getReservationId() != null) {
            java.util.Optional<Reservation> existingOpt = reservationRepository.findById(reservation.getReservationId());
            if (existingOpt.isPresent()) {
                Reservation existing = existingOpt.get();
                if (existing.getParkingSlot() != null && !existing.getParkingSlot().getSlotId().equals(slotId)) {
                    ParkingSlot originalSlot = existing.getParkingSlot();
                    boolean hasOtherActiveNow = reservationRepository.findOverlappingReservations(
                            originalSlot.getSlotId(), now, now, activeStatuses
                    ).stream().anyMatch(r -> !r.getReservationId().equals(reservation.getReservationId()));
                    
                    if (!hasOtherActiveNow) {
                        originalSlot.setStatus(SlotStatus.AVAILABLE);
                        parkingSlotRepository.save(originalSlot);
                    }
                }
            }
        }

        // Fetch and associate vehicle if present
        if (reservation.getVehicle() != null && reservation.getVehicle().getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(reservation.getVehicle().getVehicleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + reservation.getVehicle().getVehicleId()));
            reservation.setVehicle(vehicle);
        }

        reservation.setUser(user);
        reservation.setParkingSlot(slot);

        // Update current slot status based on booking status and timing
        if (reservation.getStatus() == ReservationStatus.CANCELLED || reservation.getStatus() == ReservationStatus.COMPLETED) {
            boolean hasOtherActiveNow = reservationRepository.findOverlappingReservations(
                    slotId, now, now, activeStatuses
            ).stream().anyMatch(r -> reservation.getReservationId() == null || !r.getReservationId().equals(reservation.getReservationId()));
            
            if (!hasOtherActiveNow) {
                slot.setStatus(SlotStatus.AVAILABLE);
            }
        } else {
            // CONFIRMED or PENDING
            if (!now.isBefore(reservation.getStartTime()) && !now.isAfter(reservation.getEndTime())) {
                slot.setStatus(SlotStatus.RESERVED);
            } else {
                // If it is a future booking, check if there is another currently active booking
                boolean hasOtherActiveNow = reservationRepository.findOverlappingReservations(
                        slotId, now, now, activeStatuses
                ).stream().anyMatch(r -> reservation.getReservationId() == null || !r.getReservationId().equals(reservation.getReservationId()));
                
                if (!hasOtherActiveNow) {
                    slot.setStatus(SlotStatus.AVAILABLE);
                }
            }
        }
        parkingSlotRepository.save(slot);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found with id: " + id));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found with id: " + id));

        ParkingSlot slot = reservation.getParkingSlot();
        slot.setStatus(SlotStatus.AVAILABLE);
        parkingSlotRepository.save(slot);

        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> getActiveReservationsForSlot(Long slotId) {
        LocalDateTime now = LocalDateTime.now();
        List<ReservationStatus> activeStatuses = java.util.Arrays.asList(ReservationStatus.CONFIRMED, ReservationStatus.PENDING);
        return reservationRepository.findOverlappingReservations(
                slotId,
                now.minusDays(1),
                now.plusDays(30),
                activeStatuses
        );
    }
}