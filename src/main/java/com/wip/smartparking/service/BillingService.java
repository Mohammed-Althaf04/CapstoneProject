package com.wip.smartparking.service;

import java.util.List;

import com.wip.smartparking.entity.Billing;
/**
 * Service interface defining the business contract and operations for Billing management.
 *
 * @author Naveen Muthu
 */

public interface BillingService {

    Billing generateBill(Long recordId);
    Billing getBillById(Long id);
    List<Billing> getAllBills();
    void deleteBill(Long id);
    Billing saveBill(Billing bill);
}