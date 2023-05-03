package com.basics.turfbooking.service;

import com.basics.turfbooking.dto.CustomerRequestDto;
import com.basics.turfbooking.dto.CustomerResponseDto;
import com.basics.turfbooking.exceptions.ResourceNotFoundException;


import java.util.List;

public interface CustomerService {

    CustomerResponseDto saveCustomer(CustomerRequestDto customerRequestDto);
    List<CustomerResponseDto> getAllCustomers(Integer pageNumber,Integer pageSize);

    CustomerResponseDto getCustomerById(Integer id) throws ResourceNotFoundException;

    CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto,Integer id) throws ResourceNotFoundException;

    String deleteBooking(Integer id) throws ResourceNotFoundException;

    List<CustomerResponseDto> getCustomersByName(String name) ;
}
