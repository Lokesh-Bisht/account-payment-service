package dev.lokeshbisht.account_payment_service.service;

import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;

public interface UserService {

    UserAccountInfoDto findAllAccounts(String userId);
}
