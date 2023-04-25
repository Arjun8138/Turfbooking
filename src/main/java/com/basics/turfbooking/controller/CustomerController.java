package com.basics.turfbooking.controller;

import com.basics.turfbooking.dto.CustomerRequestDto;
import com.basics.turfbooking.dto.CustomerResponseDto;
import com.basics.turfbooking.exceptions.NotFoundException;
import com.basics.turfbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turfBooking/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @PostMapping("/addCustomer")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return customerService.saveCustomer(customerRequestDto);
    }

    @GetMapping("/getAllCustomers")
    public List<CustomerResponseDto> getAllCustomers(@RequestParam(value = "pageNo",defaultValue = "0",required = false)Integer pageNo,
                                                     @RequestParam(value = "pageSize",defaultValue = "1",required = false)Integer pageSize){

        List<CustomerResponseDto> customerResponseDtoList= customerService.getAllCustomers(pageNo,pageSize);
        return customerResponseDtoList;
    }

    @GetMapping("/getCustomerById/{id}")
    public CustomerResponseDto getCustomer(@PathVariable Integer id) throws NotFoundException {
        return customerService.getCustomerById(id);

    }

    @PutMapping("/updateCustomerById/{id}")
    public CustomerResponseDto updateCustomer(@PathVariable Integer id,@RequestBody CustomerRequestDto customerRequestDto) throws NotFoundException {
        return customerService.updateCustomer(customerRequestDto,id);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public String deleteCustomer(@PathVariable Integer id) throws NotFoundException {
        return customerService.deleteBooking(id);
    }
}
