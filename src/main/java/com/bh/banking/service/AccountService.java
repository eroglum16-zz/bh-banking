package com.bh.banking.service;

import com.bh.banking.entity.Account;
import com.bh.banking.entity.Customer;
import com.bh.banking.repository.AccountRepository;
import com.bh.banking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    private static final String CURRENT = "current";
    private static final int ACCOUNT_NUMBER_LENGTH = 8;

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account openNewCurrentAccount(Long customerId, BigDecimal initialCredit) {
        return new Account(
                generateUniqueAccountNumber(),
                BigDecimal.ZERO,
                CURRENT,
                LocalDateTime.now(),
                customerRepository.getOne(customerId));
    }

    private String generateUniqueAccountNumber() {
        Random rand = new Random();
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(digits[rand.nextInt(digits.length)]);
        }
        if (accountRepository.existsByAccountNumber(accountNumber.toString())) {
            return generateUniqueAccountNumber();
        }
        return accountNumber.toString();
    }
}
