package com.wip.smartparking.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wip.smartparking.service.PaymentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * REST Controller exposing API endpoints for CRUD and business operations on Payment resources.
 *
 * @author Naveen Muthu
 */

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order/{billId}")
    public ResponseEntity<Map<String, Object>> createOrder(@PathVariable Long billId) {
        Map<String, Object> orderDetails = paymentService.createOrder(billId);
        return ResponseEntity.ok(orderDetails);
    }

    @PostMapping("/verify-payment")
    public ResponseEntity<Map<String, String>> verifyPayment(@Valid @RequestBody PaymentVerificationRequest request) {
        boolean isValid = paymentService.verifyPayment(
                request.getRazorpayOrderId(),
                request.getRazorpayPaymentId(),
                request.getRazorpaySignature(),
                request.getBillId()
        );

        Map<String, String> response = new HashMap<>();
        if (isValid) {
            response.put("status", "success");
            response.put("message", "Payment verified and transaction completed successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "failed");
            response.put("message", "Payment verification failed.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Request payload structure for verification
    public static class PaymentVerificationRequest {
        
        @NotBlank(message = "Razorpay Order ID is required")
        private String razorpayOrderId;

        @NotBlank(message = "Razorpay Payment ID is required")
        private String razorpayPaymentId;

        @NotBlank(message = "Razorpay Signature is required")
        private String razorpaySignature;

        @NotNull(message = "Bill ID is required")
        private Long billId;

        public String getRazorpayOrderId() {
            return razorpayOrderId;
        }

        public void setRazorpayOrderId(String razorpayOrderId) {
            this.razorpayOrderId = razorpayOrderId;
        }

        public String getRazorpayPaymentId() {
            return razorpayPaymentId;
        }

        public void setRazorpayPaymentId(String razorpayPaymentId) {
            this.razorpayPaymentId = razorpayPaymentId;
        }

        public String getRazorpaySignature() {
            return razorpaySignature;
        }

        public void setRazorpaySignature(String razorpaySignature) {
            this.razorpaySignature = razorpaySignature;
        }

        public Long getBillId() {
            return billId;
        }

        public void setBillId(Long billId) {
            this.billId = billId;
        }
    }
}
