package com.bh.banking.endpoint;

import com.bh.banking.dto.AccountDto;
import com.bh.banking.dto.CreateAccountDto;
import com.bh.banking.entity.Account;
import com.bh.banking.mapper.AccountMapper;
import com.bh.banking.service.AccountService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AccountController.BASE_URL)
public class AccountController {
    static final String BASE_URL = "/api/account";

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/new")
    public ResponseEntity<AccountDto> createNewAccount(@RequestBody CreateAccountDto createAccountDto) {
        Account account = accountService.openNewCurrentAccount(createAccountDto.getCustomerId(),
                createAccountDto.getInitialCredit());

        return new ResponseEntity<>(AccountMapper.INSTANCE.toAccountDto(account), HttpStatus.CREATED);
    }
}
