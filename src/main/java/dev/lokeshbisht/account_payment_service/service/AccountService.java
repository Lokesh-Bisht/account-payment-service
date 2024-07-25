package dev.lokeshbisht.account_payment_service.service;

import dev.lokeshbisht.account_payment_service.dto.request.CreateAccountRequestDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.dto.response.AccountDto;

public interface AccountService {

    UserAccountInfoDto createAccount(CreateAccountRequestDto createAccountRequestDto);

    AccountDto getAccountById(Long id);
}