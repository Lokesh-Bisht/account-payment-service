package dev.lokeshbisht.account_payment_service.controller;

import dev.lokeshbisht.account_payment_service.dto.request.CreateAccountRequestDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/")
    public UserAccountInfoDto createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto) {
        return accountService.createAccount(createAccountRequestDto);
    }
}
