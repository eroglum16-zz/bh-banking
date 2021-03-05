package com.bh.banking.service;

import com.bh.banking.repository.AccountRepository;

import org.springframework.stereotype.Service;

@Service
public class RandomAccountNumberGenerator implements AccountNumberGenerator {
    private static final int RANDOM_UPPER_BOUND = 10;

    private final AccountRepository accountRepository;

    public RandomAccountNumberGenerator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String generateUniqueAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(rand.nextInt(RANDOM_UPPER_BOUND));
        }
        if (accountRepository.existsByAccountNumber(accountNumber.toString())) {
            return generateUniqueAccountNumber();
        }
        return accountNumber.toString();
    }
}
