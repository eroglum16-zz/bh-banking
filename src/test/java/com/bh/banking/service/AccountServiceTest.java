package com.bh.banking.service;

import com.bh.banking.entity.Account;
import com.bh.banking.entity.Customer;
import com.bh.banking.repository.AccountRepository;
import com.bh.banking.repository.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountNumberGenerator accountNumberGenerator;

    @InjectMocks
    private AccountService accountService;

    @Test
    void openNewCurrentAccountTest_throwsEntityNotFoundException_WhenCustomerIsNotFound() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> accountService.openNewCurrentAccount(1L, BigDecimal.ZERO));
    }

    @Test
    void openNewCurrentAccountTest_callsSaveAndFlush_WhenCustomerIdIsValid() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(mock(Customer.class)));

        when(accountNumberGenerator.generateUniqueAccountNumber())
                .thenReturn(anyString());

        accountService.openNewCurrentAccount(1L, BigDecimal.ZERO);

        verify(accountRepository).saveAndFlush(any(Account.class));
    }
}