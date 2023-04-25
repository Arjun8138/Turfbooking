package com.basics.turfbooking.dto;

import lombok.Data;


@Data
public class BookingResponseDto {
    String bookedDate;
    String bookedTime;
    String customerName;
    String turfName;
}
