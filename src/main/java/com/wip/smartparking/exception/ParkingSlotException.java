package com.wip.smartparking.exception;
/**
 * Custom exception class representing error states specifically related to ParkingSlotException.
 *
 * @author Naveen Muthu
 */

public class ParkingSlotException extends RuntimeException {

    public ParkingSlotException(String message) {
        super(message);
    }
}