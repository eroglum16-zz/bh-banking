package com.bh.banking.mapper;

import com.bh.banking.dto.AccountDto;
import com.bh.banking.dto.CustomerDto;
import com.bh.banking.entity.Account;
import com.bh.banking.entity.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toCustomer(CustomerDto customerDto);

    CustomerDto toCustomerDto(Customer customer);

    @Mapping(target = "ownerId", ignore = true)
    AccountDto accountToAccountDto(Account account);
}
