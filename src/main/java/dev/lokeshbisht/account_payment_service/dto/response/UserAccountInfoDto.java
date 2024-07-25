package dev.lokeshbisht.account_payment_service.dto.response;

import dev.lokeshbisht.account_payment_service.entity.Account;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserAccountInfoDto {

    private String userId;

    private String username;

    private String email;

    private List<Account> accounts;
}
