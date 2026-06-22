package com.wip.smartparking.service;

import java.util.Map;

public interface PaymentService {
    Map<String, Object> createOrder(Long billId);
    boolean verifyPayment(String orderId, String paymentId, String signature, Long billId);
}
