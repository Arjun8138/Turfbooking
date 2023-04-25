package com.basics.turfbooking.service;

import com.basics.turfbooking.dto.BookingRequestDto;
import com.basics.turfbooking.dto.BookingResponseDto;
import com.basics.turfbooking.exceptions.AlreadyExistException;
import com.basics.turfbooking.exceptions.NotFoundException;

import java.text.ParseException;
import java.util.List;

public interface BookingService {


    BookingResponseDto saveBooking(BookingRequestDto bookingRequestDto) throws AlreadyExistException;

    BookingResponseDto getBookingById(Integer id) throws NotFoundException;

    List<BookingResponseDto> getAllBookings(Integer pageNo,Integer pageSize);

    BookingResponseDto updateBooking(Integer id, BookingRequestDto bookingRequestDto) throws ParseException, NotFoundException;

    String deleteBooking(Integer id) throws NotFoundException;
}
