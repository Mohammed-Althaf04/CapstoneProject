package com.wip.smartparking.service;

import java.util.Map;
/**
 * Service interface defining the business contract and operations for Payment management.
 *
 * @author Naveen Muthu
 */

public interface PaymentService {
    Map<String, Object> createOrder(Long billId);
    boolean verifyPayment(String orderId, String paymentId, String signature, Long billId);
}
