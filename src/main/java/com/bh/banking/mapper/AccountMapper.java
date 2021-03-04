package com.bh.banking.mapper;

import com.bh.banking.dto.AccountDto;
import com.bh.banking.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toAccount(AccountDto accountDto);

    AccountDto toAccountDto(Account account);
}
