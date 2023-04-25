package com.basics.turfbooking.exceptions;

public class AlreadyExistException extends Exception{

    public AlreadyExistException(String timeSlot,String message) {
        super(message);
    }
}
