package com.wip.smartparking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wip.smartparking.entity.Billing;
import com.wip.smartparking.exception.ResourceNotFoundException;
import com.wip.smartparking.repository.BillingRepository;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Override
    public Billing saveBill(Billing bill) {
        return billingRepository.save(bill);
    }

    @Override
    public Billing getBillById(Long id) {
        return billingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bill not found with id : " + id));
    }

    @Override
    public List<Billing> getAllBills() {
        return billingRepository.findAll();
    }

    @Override
    public void deleteBill(Long id) {

        Billing bill = billingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bill not found with id : " + id));

        billingRepository.delete(bill);
    }
}