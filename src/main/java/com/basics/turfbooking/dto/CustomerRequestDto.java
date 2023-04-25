package com.basics.turfbooking.dto;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private String name;
    private String address;
    private String password;
    private String mailId;
    private long contactNumber;
}
