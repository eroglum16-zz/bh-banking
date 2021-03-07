package com.bh.banking.service;

import com.bh.banking.entity.Account;
import com.bh.banking.entity.Transaction;

import com.bh.banking.repository.AccountRepository;
import com.bh.banking.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account sendTransactionToAccount(Account account, BigDecimal amount, String description) {
        account = addAmountToAccount(account, amount);

        Transaction transaction = new Transaction(amount, account.getBalance(), LocalDateTime.now(), description, account);
        transaction = transactionRepository.save(transaction);
        log.info("New transaction: {}", transaction);

        return account;
    }

    private Account addAmountToAccount(Account account, BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        balance = balance.add(amount);
        account.setBalance(balance);

        return accountRepository.saveAndFlush(account);
    }
}
