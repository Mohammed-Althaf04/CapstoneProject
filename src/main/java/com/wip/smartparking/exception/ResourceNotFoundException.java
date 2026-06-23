package com.wip.smartparking.exception;
/**
 * Custom exception class representing error states specifically related to ResourceNotFoundException.
 *
 * @author Naveen Muthu
 */

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}