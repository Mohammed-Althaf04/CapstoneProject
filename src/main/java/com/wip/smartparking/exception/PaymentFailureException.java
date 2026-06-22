package com.wip.smartparking.exception;

public class PaymentFailureException extends RuntimeException {

    public PaymentFailureException(String message) {
        super(message);
    }
}