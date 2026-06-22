package com.wip.smartparking.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wip.smartparking.entity.Billing;
import com.wip.smartparking.enums.BillingStatus;
import com.wip.smartparking.exception.ResourceNotFoundException;
import com.wip.smartparking.repository.BillingRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private BillingRepository billingRepository;

    @Override
    public Map<String, Object> createOrder(Long billId) {
        Billing billing = billingRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));

        if (billing.getBillingStatus() == BillingStatus.PAID) {
            throw new IllegalStateException("Bill is already paid");
        }

        // Generate a mock order ID
        String mockOrderId = "order_mock_" + UUID.randomUUID().toString().substring(0, 8);
        
        // Save the generated mock order ID to the billing record
        billing.setRazorpayOrderId(mockOrderId);
        billingRepository.save(billing);

        Map<String, Object> response = new HashMap<>();
        response.put("id", mockOrderId);
        // Razorpay works with paise (1 Rupee = 100 paise)
        response.put("amount", (int) Math.round(billing.getTotalAmount() * 100));
        response.put("currency", "INR");
        response.put("billId", billId);

        return response;
    }

    @Override
    public boolean verifyPayment(String orderId, String paymentId, String signature, Long billId) {
        Billing billing = billingRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));

        // In a real Razorpay integration, we would verify the signature using Razorpay SDK's signature verification.
        // Since we are mocking the API, we will log the verification request and mark it as verified.
        System.out.println("Verifying mock payment transaction:");
        System.out.println("Order ID: " + orderId);
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Signature: " + signature);

        billing.setRazorpayOrderId(orderId);
        billing.setRazorpayPaymentId(paymentId);
        billing.setRazorpaySignature(signature);
        billing.setBillingStatus(BillingStatus.PAID);

        billingRepository.save(billing);
        return true;
    }
}
