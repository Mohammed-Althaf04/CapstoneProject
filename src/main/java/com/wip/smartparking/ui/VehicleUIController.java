package com.wip.smartparking.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wip.smartparking.dto.request.VehicleRequestDTO;
import com.wip.smartparking.entity.Vehicle;
import com.wip.smartparking.entity.User;
import com.wip.smartparking.repository.UserRepository;
import com.wip.smartparking.service.UserService;
import com.wip.smartparking.service.VehicleService;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui/vehicles")
public class VehicleUIController {

    @Autowired
    private VehicleService vehicleService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String list(Model model, Principal principal) {
        String username = principal.getName();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        if (!"admin".equals(username)) {
            userRepository.findByEmail(username).ifPresent(user -> {
                if (user.getRole() == com.wip.smartparking.enums.UserRole.USER) {
                    List<Vehicle> filtered = vehicles.stream()
                            .filter(v -> v.getUser() != null && v.getUser().getUserId().equals(user.getUserId()))
                            .collect(Collectors.toList());
                    vehicles.clear();
                    vehicles.addAll(filtered);
                }
            });
        }

        model.addAttribute("vehicles", vehicles);
        return "vehicle/list";
    }

    @GetMapping("/new")
    public String form(Model model, Principal principal) {
        String username = principal.getName();
        VehicleRequestDTO vehicle = new VehicleRequestDTO();
        boolean isCustomer = false;
        
        if ("customer".equals(username)) {
            isCustomer = true;
            userRepository.findByEmail(username).ifPresent(user -> {
                vehicle.setUserId(user.getUserId());
                model.addAttribute("currentUser", user);
            });
            if (vehicle.getUserId() == null) {
                userRepository.findAll().stream()
                    .filter(u -> u.getRole() == com.wip.smartparking.enums.UserRole.USER)
                    .findFirst()
                    .ifPresent(u -> {
                        vehicle.setUserId(u.getUserId());
                        model.addAttribute("currentUser", u);
                    });
            }
        } else if (!"admin".equals(username)) {
            userRepository.findByEmail(username).ifPresent(user -> {
                if (user.getRole() == com.wip.smartparking.enums.UserRole.USER) {
                    vehicle.setUserId(user.getUserId());
                    model.addAttribute("currentUser", user);
                }
            });
            // check again if user was found and assigned
            if (vehicle.getUserId() != null) {
                isCustomer = true;
            }
        }

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("isCustomer", isCustomer);
        model.addAttribute("users", userService.getAllUsers());
        return "vehicle/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        VehicleRequestDTO dto = new VehicleRequestDTO();
        dto.setVehicleId(vehicle.getVehicleId());
        dto.setVehicleNumber(vehicle.getVehicleNumber());
        dto.setVehicleType(vehicle.getVehicleType());
        if (vehicle.getUser() != null) {
            dto.setUserId(vehicle.getUser().getUserId());
        }

        String username = principal.getName();
        boolean isCustomer = !"admin".equals(username);
        
        if (isCustomer) {
            userRepository.findByEmail(username).ifPresent(user -> {
                model.addAttribute("currentUser", user);
            });
        }

        model.addAttribute("vehicle", dto);
        model.addAttribute("isCustomer", isCustomer);
        model.addAttribute("users", userService.getAllUsers());
        return "vehicle/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("vehicle") VehicleRequestDTO dto, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            String username = principal.getName();
            boolean isCustomer = !"admin".equals(username);
            model.addAttribute("isCustomer", isCustomer);
            model.addAttribute("users", userService.getAllUsers());
            return "vehicle/form";
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(dto.getVehicleId());
        vehicle.setVehicleNumber(dto.getVehicleNumber());
        vehicle.setVehicleType(dto.getVehicleType());

        vehicleService.saveVehicle(vehicle, dto.getUserId());
        return "redirect:/ui/vehicles";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable("id") Long id) {
    	vehicleService.deleteVehicle(id);
    	return "redirect:/ui/vehicles";
    }
}