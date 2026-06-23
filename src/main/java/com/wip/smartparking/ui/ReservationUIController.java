package com.wip.smartparking.ui;

import com.wip.smartparking.entity.Reservation;
import com.wip.smartparking.entity.User;
import com.wip.smartparking.entity.ParkingSlot;
import com.wip.smartparking.entity.Vehicle;
import com.wip.smartparking.repository.UserRepository;
import com.wip.smartparking.service.ReservationService;
import com.wip.smartparking.service.UserService;
import com.wip.smartparking.service.ParkingSlotService;
import com.wip.smartparking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
/**
 * UI Controller for handling views, dashboard routing, and user interactions related to Reservation resources.
 *
 * @author althaf
 */

@Controller
@RequestMapping("/ui/reservations")
public class ReservationUIController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ParkingSlotService slotService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String list(Model model, Principal principal) {
        String username = principal.getName();
        List<Reservation> reservations = reservationService.getAllReservations();

        if (!"admin".equals(username)) {
            userRepository.findByEmail(username).ifPresent(user -> {
                if (user.getRole() == com.wip.smartparking.enums.UserRole.USER) {
                    List<Reservation> filtered = reservations.stream()
                            .filter(r -> r.getUser() != null && r.getUser().getUserId().equals(user.getUserId()))
                            .collect(Collectors.toList());
                    reservations.clear();
                    reservations.addAll(filtered);
                }
            });
        }

        model.addAttribute("reservations", reservations);
        return "reservation/list";
    }

    private void populateFormModel(Model model, Reservation reservation, Principal principal) {
        String username = principal.getName();
        boolean isCustomer = false;
        Long defaultUserId = null;
        
        if ("customer".equals(username)) {
            isCustomer = true;
            userRepository.findByEmail(username).ifPresent(user -> {
                model.addAttribute("currentUser", user);
            });
            userRepository.findAll().stream()
                .filter(u -> u.getRole() == com.wip.smartparking.enums.UserRole.USER)
                .findFirst()
                .ifPresent(u -> {
                    if (!model.containsAttribute("currentUser")) {
                        model.addAttribute("currentUser", u);
                    }
                });
        } else if (!"admin".equals(username)) {
            java.util.Optional<com.wip.smartparking.entity.User> uOpt = userRepository.findByEmail(username);
            if (uOpt.isPresent()) {
                com.wip.smartparking.entity.User user = uOpt.get();
                if (user.getRole() == com.wip.smartparking.enums.UserRole.USER) {
                    isCustomer = true;
                    defaultUserId = user.getUserId();
                    model.addAttribute("currentUser", user);
                }
            }
        }

        if (defaultUserId == null && model.containsAttribute("currentUser")) {
            com.wip.smartparking.entity.User curr = (com.wip.smartparking.entity.User) model.getAttribute("currentUser");
            if (curr != null) {
                defaultUserId = curr.getUserId();
            }
        }

        model.addAttribute("reservation", reservation);
        model.addAttribute("isCustomer", isCustomer);
        model.addAttribute("defaultUserId", defaultUserId);
        model.addAttribute("users", userService.getAllUsers());
        
        List<ParkingSlot> slots = slotService.getAllSlots();
        List<Vehicle> vehicles = java.util.Collections.emptyList();
        if (isCustomer && defaultUserId != null) {
            final Long finalUserId = defaultUserId;
            java.util.Optional<User> userOpt = userRepository.findById(finalUserId);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                vehicles = user.getVehicles();
                java.util.Set<String> vehicleTypes = user.getVehicles().stream()
                        .map(v -> v.getVehicleType().name())
                        .collect(Collectors.toSet());
                slots = slots.stream()
                        .filter(s -> (s.getStatus() == com.wip.smartparking.enums.SlotStatus.AVAILABLE 
                                  || (reservation.getParkingSlot() != null && s.getSlotId().equals(reservation.getParkingSlot().getSlotId())))
                                  && s.getSlotType() != null 
                                  && vehicleTypes.contains(s.getSlotType().name()))
                        .collect(Collectors.toList());
            } else {
                slots = slots.stream()
                        .filter(s -> s.getStatus() == com.wip.smartparking.enums.SlotStatus.AVAILABLE 
                                  || (reservation.getParkingSlot() != null && s.getSlotId().equals(reservation.getParkingSlot().getSlotId())))
                        .collect(Collectors.toList());
            }
        } else {
            slots = slots.stream()
                    .filter(s -> s.getStatus() == com.wip.smartparking.enums.SlotStatus.AVAILABLE
                              || (reservation.getParkingSlot() != null && s.getSlotId().equals(reservation.getParkingSlot().getSlotId())))
                    .collect(Collectors.toList());
            if (!isCustomer) {
                vehicles = vehicleService.getAllVehicles();
            }
        }
        
        model.addAttribute("slots", slots);
        model.addAttribute("vehicles", vehicles);
    }

    @GetMapping("/new")
    public String form(Model model, Principal principal) {
        Reservation reservation = new Reservation();
        populateFormModel(model, reservation, principal);
        return "reservation/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Reservation reservation = reservationService.getReservationById(id);
        populateFormModel(model, reservation, principal);
        return "reservation/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Reservation reservation,
                       @RequestParam Long userId,
                       @RequestParam Long slotId,
                       @RequestParam Long vehicleId,
                       @RequestParam(required = false) Long reservationId,
                       Model model,
                       Principal principal) {

        reservation.setReservationId(reservationId);
        if (vehicleId != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(vehicleId);
            reservation.setVehicle(vehicle);
        }
        
        try {
            reservationService.saveReservation(reservation, userId, slotId);
        } catch (Exception e) {
            // Restore associations for form rendering on error
            User u = new User();
            u.setUserId(userId);
            reservation.setUser(u);
            
            ParkingSlot s = new ParkingSlot();
            s.setSlotId(slotId);
            reservation.setParkingSlot(s);

            populateFormModel(model, reservation, principal);
            model.addAttribute("error", e.getMessage());
            return "reservation/form";
        }
        return "redirect:/ui/reservations";
    }
    
    @GetMapping("/active-bookings")
    @ResponseBody
    public List<com.wip.smartparking.dto.response.ReservationResponseDTO> getActiveBookingsForSlot(@RequestParam("slotId") Long slotId) {
        List<Reservation> reservations = reservationService.getActiveReservationsForSlot(slotId);
        return reservations.stream()
                .map(com.wip.smartparking.mapper.ReservationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/ui/reservations";
    }
}
