package com.bh.banking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@NoArgsConstructor
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

    @ToString.Exclude
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
