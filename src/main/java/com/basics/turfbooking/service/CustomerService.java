package com.basics.turfbooking.service;

import com.basics.turfbooking.dto.CustomerRequestDto;
import com.basics.turfbooking.dto.CustomerResponseDto;
import com.basics.turfbooking.exceptions.NotFoundException;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto saveCustomer(CustomerRequestDto customerRequestDto);
    List<CustomerResponseDto> getAllCustomers(Integer pageNumber,Integer pageSize);

    CustomerResponseDto getCustomerById(Integer id) throws NotFoundException;

    CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto,Integer id) throws NotFoundException;

    String deleteBooking(Integer id) throws NotFoundException;

}
