package dev.lokeshbisht.account_payment_service.dto.account;

import dev.lokeshbisht.account_payment_service.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDto {

    List<Account> accounts;
}
