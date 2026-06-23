package com.wip.smartparking.ui;

import com.wip.smartparking.dto.request.UserRequestDTO;
import com.wip.smartparking.entity.User;
import com.wip.smartparking.enums.UserRole;
import com.wip.smartparking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wip.smartparking.repository.UserRepository;
import com.wip.smartparking.service.BillingService;
import com.wip.smartparking.service.ParkingSlotService;
import com.wip.smartparking.service.ReservationService;
import com.wip.smartparking.service.VehicleService;
import java.security.Principal;
/**
 * UI Controller for handling views, dashboard routing, and user interactions related to Home resources.
 *
 * @author althaf
 */

@Controller
public class HomeUIController {

    @Autowired
    private UserService userService;

    @Autowired
    private ParkingSlotService parkingSlotService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/ui/home";
    }
    
    @GetMapping("/ui/home")
    public String home(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String username = principal.getName();
        
        boolean isAdmin = false;
        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            isAdmin = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        }
        
        model.addAttribute("isAdmin", isAdmin);

        java.util.Optional<User> uOpt = userRepository.findByEmailOrName(username, username);
        if (uOpt.isPresent()) {
            model.addAttribute("currentUser", uOpt.get());
        } else {
            User mockUser = new User();
            mockUser.setName(username.equals("admin") ? "System Admin" : "Default Customer");
            model.addAttribute("currentUser", mockUser);
        }

        if (isAdmin) {
            // Admin stats
            model.addAttribute("totalSlots", parkingSlotService.getAllSlots().size());
            model.addAttribute("totalVehicles", vehicleService.getAllVehicles().size());
            model.addAttribute("totalReservations", reservationService.getAllReservations().size());
            
            // Total billing revenue
            double revenue = billingService.getAllBills().stream()
                    .filter(b -> b.getBillingStatus() == com.wip.smartparking.enums.BillingStatus.PAID)
                    .mapToDouble(b -> b.getTotalAmount())
                    .sum();
            model.addAttribute("totalRevenue", revenue);
        } else {
            // Customer stats: only their own
            if (uOpt.isPresent()) {
                User user = uOpt.get();
                Long userId = user.getUserId();
                
                // Vehicles owned by this customer
                var myVehicles = vehicleService.getAllVehicles().stream()
                        .filter(v -> v.getUser() != null && v.getUser().getUserId().equals(userId))
                        .toList();
                model.addAttribute("totalVehicles", myVehicles.size());
                
                // Reservations made by this customer
                var myReservations = reservationService.getAllReservations().stream()
                        .filter(r -> r.getUser() != null && r.getUser().getUserId().equals(userId))
                        .toList();
                model.addAttribute("totalReservations", myReservations.size());
                
                // Billing: total paid
                var myBills = billingService.getAllBills().stream()
                        .filter(b -> b.getParkingRecord() != null 
                                  && b.getParkingRecord().getVehicle() != null 
                                  && b.getParkingRecord().getVehicle().getUser() != null 
                                  && b.getParkingRecord().getVehicle().getUser().getUserId().equals(userId))
                        .toList();
                model.addAttribute("totalRevenue", myBills.stream()
                        .filter(b -> b.getBillingStatus() == com.wip.smartparking.enums.BillingStatus.PAID)
                        .mapToDouble(b -> b.getTotalAmount())
                        .sum());
            } else {
                model.addAttribute("totalVehicles", 0);
                model.addAttribute("totalReservations", 0);
                model.addAttribute("totalRevenue", 0.0);
            }
            
            // Available slots count (filtered by customer's vehicle types if they have vehicles)
            long availableSlots = 0;
            if (uOpt.isPresent()) {
                User user = uOpt.get();
                java.util.Set<String> vehicleTypes = user.getVehicles().stream()
                        .map(v -> v.getVehicleType().name())
                        .collect(java.util.stream.Collectors.toSet());
                if (!vehicleTypes.isEmpty()) {
                    availableSlots = parkingSlotService.getAllSlots().stream()
                            .filter(s -> s.getStatus() == com.wip.smartparking.enums.SlotStatus.AVAILABLE 
                                      && s.getSlotType() != null 
                                      && vehicleTypes.contains(s.getSlotType().name()))
                            .count();
                } else {
                    availableSlots = parkingSlotService.getAllSlots().stream()
                            .filter(s -> s.getStatus() == com.wip.smartparking.enums.SlotStatus.AVAILABLE)
                            .count();
                }
            } else {
                availableSlots = parkingSlotService.getAllSlots().stream()
                        .filter(s -> s.getStatus() == com.wip.smartparking.enums.SlotStatus.AVAILABLE)
                        .count();
            }
            model.addAttribute("totalSlots", availableSlots);
        }
        
        return "home";
    }

    @GetMapping("/ui/register")
    public String registerForm(Model model) {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setRole(UserRole.USER); // Default to USER role for self-registration
        model.addAttribute("user", dto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute("user") UserRequestDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        
        // Check if email already exists
        if (userService.getAllUsers().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(dto.getEmail()))) {
            result.rejectValue("email", "error.user", "An account already exists with this email address.");
            return "register";
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setRole(UserRole.USER); // Enforce USER role for self-registration

        userService.saveUser(user);
        return "redirect:/login?registered=true";
    }
}