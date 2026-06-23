package com.wip.smartparking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wip.smartparking.dto.request.BillingRequestDTO;
import com.wip.smartparking.dto.response.BillingResponseDTO;
import com.wip.smartparking.entity.Billing;
import com.wip.smartparking.mapper.BillingMapper;
import com.wip.smartparking.service.BillingService;

import jakarta.validation.Valid;
/**
 * REST Controller exposing API endpoints for CRUD and business operations on Billing resources.
 *
 * @author Naveen Muthu
 */

@RestController
@RequestMapping("/billing")
@Validated
public class BillingController {

    @Autowired
    private BillingService billingService;

    // GENERATE BILL (NOT SAVE DIRECTLY)
    @PostMapping("/generate/{recordId}")
    public BillingResponseDTO generateBill(@PathVariable Long recordId) {

        Billing bill = billingService.generateBill(recordId);

        return BillingMapper.toResponseDTO(bill);
    }

    @GetMapping("getABill/{id}")
    public BillingResponseDTO getBillById(@PathVariable Long id) {

        Billing bill = billingService.getBillById(id);

        return BillingMapper.toResponseDTO(bill);
    }

    @GetMapping("listAll")
    public List<BillingResponseDTO> getAllBills() {

        return billingService.getAllBills()
                .stream()
                .map(BillingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("delete/{id}")
    public String deleteBill(@PathVariable Long id) {

        billingService.deleteBill(id);
        return "Bill deleted successfully";
    }
}