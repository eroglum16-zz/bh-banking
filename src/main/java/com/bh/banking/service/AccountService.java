package com.bh.banking.service;

import com.bh.banking.entity.Account;
import com.bh.banking.entity.Customer;
import com.bh.banking.repository.AccountRepository;
import com.bh.banking.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
public class AccountService {

    private static final String CURRENT = "current";

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountNumberGenerator accountNumberGenerator;
    private final TransactionService transactionService;
    private final String initialCreditTransactionMessage;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository,
                          AccountNumberGenerator accountNumberGenerator, TransactionService transactionService,
                          @Value("${transaction.description.initial-credit}") String initialCreditTransactionMessage) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountNumberGenerator = accountNumberGenerator;
        this.transactionService = transactionService;
        this.initialCreditTransactionMessage = initialCreditTransactionMessage;
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

        if (initialCredit != null && initialCredit.compareTo(BigDecimal.ZERO) > 0) {
            account = transactionService.sendTransactionToAccount(account, initialCredit, initialCreditTransactionMessage);
        } else {
            account = accountRepository.saveAndFlush(account);
        }
        log.info("A new account created: {}", account);
        return account;
    }
}
