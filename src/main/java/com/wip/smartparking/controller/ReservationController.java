package com.wip.smartparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wip.smartparking.dto.request.ReservationRequestDTO;
import com.wip.smartparking.dto.response.ReservationResponseDTO;
import com.wip.smartparking.entity.Reservation;
import com.wip.smartparking.mapper.ReservationMapper;
import com.wip.smartparking.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ReservationResponseDTO saveReservation(
            @Valid @RequestBody ReservationRequestDTO dto) {

        Reservation reservation = ReservationMapper.toEntity(dto);

        Reservation savedReservation =
                reservationService.saveReservation(reservation);

        return ReservationMapper.toResponseDTO(savedReservation);
    }

    @GetMapping("/{id}")
    public ReservationResponseDTO getReservationById(
            @PathVariable Long id) {

        Reservation reservation =
                reservationService.getReservationById(id);

        return ReservationMapper.toResponseDTO(reservation);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/{id}")
    public String deleteReservation(@PathVariable Long id) {

        reservationService.deleteReservation(id);

        return "Reservation deleted successfully";
    }
}