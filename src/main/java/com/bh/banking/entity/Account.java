package com.bh.banking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String accountNumber;

    private Double balance;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer owner;
}
