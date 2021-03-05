package com.bh.banking.service;

import com.bh.banking.entity.Account;
import com.bh.banking.entity.Customer;
import com.bh.banking.repository.AccountRepository;
import com.bh.banking.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private static final String CURRENT = "current";

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountNumberGenerator accountNumberGenerator;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository,
                          AccountNumberGenerator accountNumberGenerator) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    public Account openNewCurrentAccount(Long customerId, BigDecimal initialCredit) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer was not found for ID: " + customerId));
        Account account = new Account(
                accountNumberGenerator.generateUniqueAccountNumber(),
                BigDecimal.ZERO,
                CURRENT,
                LocalDateTime.now(),
                customer);

        return accountRepository.saveAndFlush(account);
    }
}
