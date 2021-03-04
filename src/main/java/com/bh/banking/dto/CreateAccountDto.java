package com.bh.banking.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountDto {
    private Long customerId;
    private BigDecimal initialCredit;
}
