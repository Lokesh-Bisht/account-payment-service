package dev.lokeshbisht.account_payment_service.service;

import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountPermissionInfoDto;

public interface UserService {

    UserAccountPermissionInfoDto findAllAccounts(String userId);
}
