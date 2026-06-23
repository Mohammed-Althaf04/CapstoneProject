package com.wip.smartparking.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wip.smartparking.entity.Billing;
import com.wip.smartparking.enums.BillingStatus;
import com.wip.smartparking.exception.ResourceNotFoundException;
import com.wip.smartparking.repository.BillingRepository;
/**
 * Service interface defining the business contract and operations for PaymentImplTest management.
 *
 * @author althaf
 */

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Billing pendingBill;
    private Billing paidBill;

    @BeforeEach
    void setUp() {
        pendingBill = new Billing();
        pendingBill.setBillId(1L);
        pendingBill.setTotalAmount(150.0);
        pendingBill.setBillingStatus(BillingStatus.PENDING);

        paidBill = new Billing();
        paidBill.setBillId(2L);
        paidBill.setTotalAmount(200.0);
        paidBill.setBillingStatus(BillingStatus.PAID);
    }

    @Test
    void createOrder_Success() {
        when(billingRepository.findById(1L)).thenReturn(Optional.of(pendingBill));
        when(billingRepository.save(any(Billing.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Map<String, Object> order = paymentService.createOrder(1L);

        assertNotNull(order);
        assertTrue(order.containsKey("id"));
        assertTrue(order.get("id").toString().startsWith("order_mock_"));
        assertEquals(15000, order.get("amount")); // 150.0 * 100
        assertEquals("INR", order.get("currency"));
        assertEquals(1L, order.get("billId"));

        verify(billingRepository, times(1)).findById(1L);
        verify(billingRepository, times(1)).save(pendingBill);
    }

    @Test
    void createOrder_BillNotFound_ThrowsException() {
        when(billingRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            paymentService.createOrder(99L);
        });

        verify(billingRepository, times(1)).findById(99L);
        verify(billingRepository, never()).save(any());
    }

    @Test
    void createOrder_BillAlreadyPaid_ThrowsException() {
        when(billingRepository.findById(2L)).thenReturn(Optional.of(paidBill));

        assertThrows(IllegalStateException.class, () -> {
            paymentService.createOrder(2L);
        });

        verify(billingRepository, times(1)).findById(2L);
        verify(billingRepository, never()).save(any());
    }

    @Test
    void verifyPayment_Success() {
        when(billingRepository.findById(1L)).thenReturn(Optional.of(pendingBill));
        when(billingRepository.save(any(Billing.class))).thenAnswer(invocation -> invocation.getArgument(0));

        boolean result = paymentService.verifyPayment("order_123", "pay_123", "sig_123", 1L);

        assertTrue(result);
        assertEquals(BillingStatus.PAID, pendingBill.getBillingStatus());
        assertEquals("order_123", pendingBill.getRazorpayOrderId());
        assertEquals("pay_123", pendingBill.getRazorpayPaymentId());
        assertEquals("sig_123", pendingBill.getRazorpaySignature());

        verify(billingRepository, times(1)).findById(1L);
        verify(billingRepository, times(1)).save(pendingBill);
    }

    @Test
    void verifyPayment_BillNotFound_ThrowsException() {
        when(billingRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            paymentService.verifyPayment("order_123", "pay_123", "sig_123", 99L);
        });

        verify(billingRepository, times(1)).findById(99L);
        verify(billingRepository, never()).save(any());
    }
}
