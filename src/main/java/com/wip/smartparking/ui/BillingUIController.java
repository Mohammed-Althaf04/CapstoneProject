package com.wip.smartparking.ui;

import com.wip.smartparking.service.BillingService;
import com.wip.smartparking.service.ParkingRecordService;
import com.wip.smartparking.entity.Billing;
import com.wip.smartparking.entity.User;
import com.wip.smartparking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
/**
 * UI Controller for handling views, dashboard routing, and user interactions related to Billing resources.
 *
 * @author althaf
 */

@Controller
@RequestMapping("/ui/billing")
public class BillingUIController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String list(Model model, Principal principal) {
        String username = principal.getName();
        List<Billing> bills = billingService.getAllBills();

        boolean isAdmin = false;
        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            isAdmin = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);

        if (!"admin".equals(username)) {
            userRepository.findByEmail(username).ifPresent(user -> {
                if (user.getRole() == com.wip.smartparking.enums.UserRole.USER) {
                    List<Billing> filtered = bills.stream()
                            .filter(b -> b.getParkingRecord() != null 
                                      && b.getParkingRecord().getVehicle() != null 
                                      && b.getParkingRecord().getVehicle().getUser() != null 
                                      && b.getParkingRecord().getVehicle().getUser().getUserId().equals(user.getUserId()))
                            .collect(Collectors.toList());
                    bills.clear();
                    bills.addAll(filtered);
                }
            });
        }

        model.addAttribute("bills", bills);
        return "billing/list";
    }

    @GetMapping("/generate/{recordId}")
    public String generate(@PathVariable Long recordId) {

        billingService.generateBill(recordId);

        return "redirect:/ui/billing";
    }
    
    @GetMapping("/pay/{billId}")
    public String payBill(@PathVariable Long billId) {

        Billing bill = billingService.getBillById(billId);

        bill.setBillingStatus(
                com.wip.smartparking.enums.BillingStatus.PAID);

        billingService.saveBill(bill);

        return "redirect:/ui/billing";
    }
}