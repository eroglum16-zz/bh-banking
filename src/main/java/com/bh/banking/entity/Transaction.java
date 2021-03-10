package com.bh.banking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
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
