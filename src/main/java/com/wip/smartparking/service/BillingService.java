package com.wip.smartparking.service;

import java.util.List;

import com.wip.smartparking.entity.Billing;

public interface BillingService {

    Billing saveBill(Billing bill);

    Billing getBillById(Long id);

    List<Billing> getAllBills();

    void deleteBill(Long id);
}