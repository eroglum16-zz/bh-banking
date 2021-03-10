package com.bh.banking.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private Long transactionId;

    private BigDecimal amount;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    private String description;
}
