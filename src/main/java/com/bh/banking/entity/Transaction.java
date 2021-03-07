package com.bh.banking.entity;

import lombok.Data;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private BigDecimal amount;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    public Transaction(BigDecimal amount, BigDecimal balance, LocalDateTime createdAt, String description, Account account) {
        this.amount = amount;
        this.balance = balance;
        this.createdAt = createdAt;
        this.description = description;
        this.account = account;
    }
}
