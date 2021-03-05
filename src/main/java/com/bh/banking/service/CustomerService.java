package com.bh.banking.service;

import com.bh.banking.entity.Customer;
import com.bh.banking.repository.CustomerRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }
}
