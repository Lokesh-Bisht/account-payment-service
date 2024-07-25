package dev.lokeshbisht.account_payment_service.controller;

import dev.lokeshbisht.account_payment_service.dto.request.CreateAccountRequestDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.dto.response.AccountDto;
import dev.lokeshbisht.account_payment_service.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public UserAccountInfoDto createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto) {
        return accountService.createAccount(createAccountRequestDto);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
}