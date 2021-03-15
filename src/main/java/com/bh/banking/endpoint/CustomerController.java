package com.bh.banking.endpoint;

import com.bh.banking.dto.CustomerDto;
import com.bh.banking.entity.Customer;
import com.bh.banking.mapper.CustomerMapper;
import com.bh.banking.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    static final String BASE_URL = "/api/customer";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.save(CustomerMapper.INSTANCE.toCustomer(customerDto));
        log.info("A new customer created: {}", customer);

        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerDto(customer), HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getById(customerId);

        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerDto(customer), HttpStatus.OK);
    }
}
