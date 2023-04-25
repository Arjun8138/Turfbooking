package com.basics.turfbooking.dto;

import lombok.Data;

import java.util.Date;
@Data
public class BookingRequestDto {
    private Date bookedDateAndTime;
    int customerId;
    int turfId;
}
