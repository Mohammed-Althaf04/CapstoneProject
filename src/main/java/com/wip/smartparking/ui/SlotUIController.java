package com.wip.smartparking.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.wip.smartparking.dto.request.ParkingSlotRequestDTO;
import com.wip.smartparking.entity.ParkingSlot;
import com.wip.smartparking.entity.User;
import com.wip.smartparking.repository.UserRepository;
import com.wip.smartparking.service.ParkingSlotService;
/**
 * UI Controller for handling views, dashboard routing, and user interactions related to Slot resources.
 *
 * @author althaf
 */

@Controller
@RequestMapping("/ui/slots")
public class SlotUIController {

    @Autowired
    private ParkingSlotService parkingSlotService;  

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String list(Model model, java.security.Principal principal) {
        boolean isAdmin = false;
        if (principal != null) {
            org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                isAdmin = auth.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            }
        }
        model.addAttribute("isAdmin", isAdmin);
        
        java.util.List<ParkingSlot> slots = parkingSlotService.getAllSlots();
        if (principal != null && !isAdmin) {
            String username = principal.getName();
            java.util.Optional<User> uOpt = userRepository.findByEmail(username);
            if (uOpt.isPresent()) {
                User user = uOpt.get();
                if (user.getRole() == com.wip.smartparking.enums.UserRole.USER) {
                    java.util.Set<String> vehicleTypes = user.getVehicles().stream()
                            .map(v -> v.getVehicleType().name())
                            .collect(java.util.stream.Collectors.toSet());
                    slots = slots.stream()
                            .filter(s -> s.getSlotType() != null && vehicleTypes.contains(s.getSlotType().name()))
                            .collect(java.util.stream.Collectors.toList());
                }
            }
        }
        
        model.addAttribute("slots", slots);
        return "parkingslot/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("parkingSlot", new ParkingSlotRequestDTO());
        return "parkingslot/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("parkingSlot") ParkingSlotRequestDTO dto, org.springframework.validation.BindingResult result) {
        if (result.hasErrors()) {
            return "parkingslot/form";
        }
        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber(dto.getSlotNumber());
        slot.setSlotType(dto.getSlotType());
        slot.setFloorNo(dto.getFloorNo());
        slot.setStatus(dto.getStatus());
        parkingSlotService.saveSlot(slot);
        return "redirect:/ui/slots";
    }

    @GetMapping("/delete/{id}")
    public String deleteSlot(@PathVariable Long id) {
        parkingSlotService.deleteSlot(id);
        return "redirect:/ui/slots";
    }
}