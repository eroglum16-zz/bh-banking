package com.bh.banking.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountDto {
    private Long accountId;

    private String accountNumber;

    private BigDecimal balance;

    private String type;

    private LocalDateTime createdAt;

    private Long ownerId;
}
