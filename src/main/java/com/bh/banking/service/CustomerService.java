package com.bh.banking.service;

import com.bh.banking.entity.Customer;
import com.bh.banking.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    public Customer getById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer was not found for ID: " + customerId));
    }
}
