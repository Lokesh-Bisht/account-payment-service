package dev.lokeshbisht.account_payment_service.service.impl;

import dev.lokeshbisht.account_payment_service.dto.request.CreateAccountRequestDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    public static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public UserAccountInfoDto createAccount(CreateAccountRequestDto createAccountRequestDto) {
        return null;
    }
}
