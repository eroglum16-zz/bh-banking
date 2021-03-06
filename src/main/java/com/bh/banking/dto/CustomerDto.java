package com.bh.banking.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long customerId;

    private String name;

    private String surname;

    private String email;

    private String phone;
}
