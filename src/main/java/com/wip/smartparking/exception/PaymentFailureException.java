package com.wip.smartparking.exception;
/**
 * Custom exception class representing error states specifically related to PaymentFailureException.
 *
 * @author Naveen Muthu
 */

public class PaymentFailureException extends RuntimeException {

    public PaymentFailureException(String message) {
        super(message);
    }
}