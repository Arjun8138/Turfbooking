package com.basics.turfbooking.controller;

import com.basics.turfbooking.dto.BookingRequestDto;
import com.basics.turfbooking.dto.BookingResponseDto;
import com.basics.turfbooking.exceptions.AlreadyExistException;
import com.basics.turfbooking.exceptions.ResourceNotFoundException;
import com.basics.turfbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/turf/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/addBooking")
    public BookingResponseDto saveBooking(@RequestBody BookingRequestDto bookingRequestDto) throws AlreadyExistException {
        return bookingService.saveBooking(bookingRequestDto);
    }
    @GetMapping("/getBookingById/{id}")
    public BookingResponseDto bookingResponseDto(@PathVariable Integer id) throws ResourceNotFoundException {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/getAllBookings")
    public List<BookingResponseDto> bookingResponseDto(@RequestParam(value = "pageNo",defaultValue = "1",required = false)Integer pageNo,
                                                      @RequestParam(value = "pageSize",defaultValue = "1",required = false)Integer pageSize){
        return bookingService.getAllBookings(pageNo,pageSize);
    }

    @PutMapping("/updateBooking/{id}")
    public BookingResponseDto bookingResponseDto(@PathVariable Integer id,@RequestBody BookingRequestDto bookingRequestDto) throws ParseException, ResourceNotFoundException {
        return bookingService.updateBooking(id,bookingRequestDto);
    }

    @DeleteMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable Integer id) throws ResourceNotFoundException {
        return bookingService.deleteBooking(id);
    }

}
