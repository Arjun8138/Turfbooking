package com.basics.turfbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String TimeSlot;
    private String bookedDate;

    @ManyToOne
    @JoinColumn(name = "customerId",referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "turfId",referencedColumnName = "id")
    private Turf turf;
}
