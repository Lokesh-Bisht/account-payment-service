package dev.lokeshbisht.account_payment_service.dto.response;

import dev.lokeshbisht.account_payment_service.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountInfoDto {

    private String userId;

    private String username;

    private String email;

    private List<Account> accounts;
}
