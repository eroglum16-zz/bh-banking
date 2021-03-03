package com.bh.banking.entity;

import lombok.Data;

import org.hibernate.annotations.NaturalId;

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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @NaturalId
    private String accountNumber;

    private BigDecimal balance;

    private String type;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer owner;

    public Account(String accountNumber, BigDecimal balance, String type, LocalDateTime createdAt, Customer owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.createdAt = createdAt;
        this.owner = owner;
    }
}
