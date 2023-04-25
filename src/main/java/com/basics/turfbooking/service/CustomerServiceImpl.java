package com.basics.turfbooking.service;

import com.basics.turfbooking.dto.CustomerRequestDto;
import com.basics.turfbooking.dto.CustomerResponseDto;
import com.basics.turfbooking.entity.Customer;
import com.basics.turfbooking.exceptions.NotFoundException;
import com.basics.turfbooking.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerResponseDto saveCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = new Customer();
        customer.setAddress(customerRequestDto.getAddress());
        customer.setName(customerRequestDto.getName());
        customer.setPassword(customerRequestDto.getPassword());
        customer.setMailId(customerRequestDto.getMailId());
        customer.setContactNumber(customerRequestDto.getContactNumber());
        customerRepository.save(customer);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setAddress(customerRequestDto.getAddress());
        customerResponseDto.setName(customerRequestDto.getName());
        customerResponseDto.setContactNumber(customer.getContactNumber());
        return customerResponseDto;

    }

    @Override
    public List<CustomerResponseDto> getAllCustomers(Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        Page<Customer> page = customerRepository.findAll(pageRequest);
        List<Customer> customers = page.getContent();

        List<CustomerResponseDto> customerResponseDtoList = customers.stream().
                map(customer -> modelMapper.map(customer,CustomerResponseDto.class)).collect(Collectors.toList());
        return customerResponseDtoList;
    }

    @Override
    public CustomerResponseDto getCustomerById(Integer id) throws NotFoundException {

        Customer customer=customerRepository.findById(id).
                orElseThrow(()->new NotFoundException("Customer Not found with :",id));
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setAddress(customer.getAddress());
        customerResponseDto.setContactNumber(customer.getContactNumber());
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto,Integer id) throws NotFoundException {

        Customer customer = customerRepository.findById(id).
                orElseThrow(()->new NotFoundException("Customer Not found with :",id));
        customer.setName(customerRequestDto.getName());
        customer.setAddress(customerRequestDto.getAddress());
        customer.setMailId(customerRequestDto.getMailId());
        customer.setPassword(customerRequestDto.getPassword());
        customer.setContactNumber(customerRequestDto.getContactNumber());
        customerRepository.save(customer);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setAddress(customer.getAddress());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setContactNumber(customer.getContactNumber());
        return customerResponseDto;
    }

    @Override
    public String deleteBooking(Integer id) throws NotFoundException {

        Customer customer = customerRepository.findById(id).
                orElseThrow(()->new NotFoundException("Customer Not found with :",id));
        customerRepository.delete(customer);
        return "Customer deleted Successfully";
    }

}
