package com.wip.smartparking.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wip.smartparking.service.PaymentService;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaymentService paymentService;

    @MockitoBean
    private com.wip.smartparking.security.JwtService jwtService;

    @MockitoBean
    private com.wip.smartparking.security.CustomUserDetailsService customUserDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createOrder_Success() throws Exception {
        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("id", "order_mock_12345");
        orderDetails.put("amount", 15000);
        orderDetails.put("currency", "INR");
        orderDetails.put("billId", 1L);

        when(paymentService.createOrder(1L)).thenReturn(orderDetails);

        mockMvc.perform(post("/api/payment/create-order/1")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("order_mock_12345"))
                .andExpect(jsonPath("$.amount").value(15000))
                .andExpect(jsonPath("$.currency").value("INR"))
                .andExpect(jsonPath("$.billId").value(1));

        verify(paymentService, times(1)).createOrder(1L);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void verifyPayment_Success() throws Exception {
        PaymentController.PaymentVerificationRequest request = new PaymentController.PaymentVerificationRequest();
        request.setRazorpayOrderId("order_mock_12345");
        request.setRazorpayPaymentId("pay_12345");
        request.setRazorpaySignature("sig_12345");
        request.setBillId(1L);

        when(paymentService.verifyPayment("order_mock_12345", "pay_12345", "sig_12345", 1L)).thenReturn(true);

        mockMvc.perform(post("/api/payment/verify-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Payment verified and transaction completed successfully."));

        verify(paymentService, times(1)).verifyPayment("order_mock_12345", "pay_12345", "sig_12345", 1L);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void verifyPayment_Failure() throws Exception {
        PaymentController.PaymentVerificationRequest request = new PaymentController.PaymentVerificationRequest();
        request.setRazorpayOrderId("order_mock_12345");
        request.setRazorpayPaymentId("pay_12345");
        request.setRazorpaySignature("sig_12345");
        request.setBillId(1L);

        when(paymentService.verifyPayment("order_mock_12345", "pay_12345", "sig_12345", 1L)).thenReturn(false);

        mockMvc.perform(post("/api/payment/verify-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("failed"))
                .andExpect(jsonPath("$.message").value("Payment verification failed."));

        verify(paymentService, times(1)).verifyPayment("order_mock_12345", "pay_12345", "sig_12345", 1L);
    }
}
