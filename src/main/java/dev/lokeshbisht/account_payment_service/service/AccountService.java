package dev.lokeshbisht.account_payment_service.service;

import dev.lokeshbisht.account_payment_service.dto.request.CreateAccountRequestDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;

public interface AccountService {

    UserAccountInfoDto createAccount(CreateAccountRequestDto createAccountRequestDto);
}
