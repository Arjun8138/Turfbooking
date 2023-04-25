package com.basics.turfbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private String openingTime;
    private String closingTime;
    private long rate;
    private String licenseNumber;
    private String ownerName;

    @OneToMany(mappedBy = "turf")
    List<Booking> bookings;

}