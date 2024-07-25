package dev.lokeshbisht.account_payment_service.service;

import dev.lokeshbisht.account_payment_service.dto.request.CreateAccountRequestDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.dto.response.AccountDto;
import dev.lokeshbisht.account_payment_service.entity.Account;

public interface AccountService {

    UserAccountInfoDto createAccount(CreateAccountRequestDto createAccountRequestDto);

    AccountDto getAccountById(Long id);
}