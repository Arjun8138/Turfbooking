package com.basics.turfbooking.dto;

import lombok.Data;

@Data
public class TurfRequestDto {

    private String name;
    private String location;
    private String openingTime;
    private String closingTime;
    private long rate;
    private String licenseNumber;
    private String ownerName;

}
