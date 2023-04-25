package com.basics.turfbooking.dto;

import lombok.Data;

@Data
public class TurfResponseDto {
    private String name;
    private String location;
    private String openingTime;
    private String closingTime;
    private long rate;
}
