package com.basics.turfbooking.service;

import com.basics.turfbooking.dto.BookingRequestDto;
import com.basics.turfbooking.dto.BookingResponseDto;
import com.basics.turfbooking.entity.Booking;
import com.basics.turfbooking.entity.Customer;
import com.basics.turfbooking.entity.Turf;
import com.basics.turfbooking.exceptions.AlreadyExistException;
import com.basics.turfbooking.exceptions.ResourceNotFoundException;
import com.basics.turfbooking.repository.BookingRepository;
import com.basics.turfbooking.repository.CustomerRepository;
import com.basics.turfbooking.repository.TurfRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TurfRepository turfRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BookingResponseDto saveBooking(BookingRequestDto bookingRequestDto) throws AlreadyExistException {

        Customer customer = customerRepository.findById(bookingRequestDto.getCustomerId())
                .orElseThrow(()->new RuntimeException("Not found"));

        Turf turf = turfRepository.findById(bookingRequestDto.getTurfId())
                .orElseThrow(()->new RuntimeException("Not found"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YY");
        String bookedDate = dateFormat.format(bookingRequestDto.getBookedDateAndTime());

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
        String bookedTime = timeFormat.format(bookingRequestDto.getBookedDateAndTime());

        List<Booking> bookingList = bookingRepository.findAll();
        for (Booking booking:bookingList) {
            if (booking.getBookedDate().equals(bookedDate)){
                if (booking.getTimeSlot().equals(bookedTime)){
                    throw new AlreadyExistException(bookedTime," : Booked slot for this time period is not vacant");
                }
            }
        }
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setTurf(turf);
        booking.setBookedDate(bookedDate);
        booking.setTimeSlot(bookedTime);
        bookingRepository.save(booking);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setBookedDate(booking.getBookedDate());
        bookingResponseDto.setBookedTime(booking.getTimeSlot());
        bookingResponseDto.setCustomerName(booking.getCustomer().getName());
        bookingResponseDto.setTurfName(booking.getTurf().getName());
        return bookingResponseDto;
    }

    @Override
    public BookingResponseDto getBookingById(Integer id) throws ResourceNotFoundException {


        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking","id",id));

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setTurfName(booking.getTurf().getName());
        bookingResponseDto.setCustomerName(booking.getCustomer().getName());
        bookingResponseDto.setBookedDate(booking.getBookedDate().toString());
        return bookingResponseDto;


    }

    @Override
    public List<BookingResponseDto> getAllBookings(Integer pageNo, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo,pageSize);
        Page<Booking> page = bookingRepository.findAll(pageRequest);
        List<Booking> bookingList = page.getContent();

        List<BookingResponseDto> bookingResponseDto = bookingList
                .stream().map(booking -> modelMapper.map(booking,BookingResponseDto.class)).
                collect(Collectors.toList());
        return bookingResponseDto;

    }

    @Override
    public BookingResponseDto updateBooking(Integer id, BookingRequestDto bookingRequestDto) throws ResourceNotFoundException {

        Booking booking=bookingRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Booking","id",id));

        Customer customer = customerRepository.findById(bookingRequestDto.getCustomerId())
                        .orElseThrow(()->new ResourceNotFoundException("Customer","id",id));

        Turf turf = turfRepository.findById(bookingRequestDto.getTurfId())
                .orElseThrow(()->new ResourceNotFoundException("Turf","id",id));


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YY");
        String date = dateFormat.format(bookingRequestDto.getBookedDateAndTime());
        booking.setBookedDate(date);
        booking.setTurf(turf);
        booking.setCustomer(customer);
        booking.setBookedDate(date);
        booking.setTimeSlot(null);
        bookingRepository.save(booking);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setBookedDate(booking.getBookedDate().toString());
        bookingResponseDto.setTurfName(booking.getTurf().getName());
        bookingResponseDto.setCustomerName(booking.getCustomer().getName());

        return bookingResponseDto;

    }

    @Override
    public String deleteBooking(Integer id) throws ResourceNotFoundException {

        Booking booking = bookingRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Booking","id",id));
        bookingRepository.delete(booking);
        return "Booking Deleted successfully";
    }
}
