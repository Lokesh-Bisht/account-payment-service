package dev.lokeshbisht.account_payment_service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lokeshbisht.account_payment_service.enums.AccountType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateAccountRequestDto {

    private String userId;

    private double balance;

    @JsonProperty("account_type")
    private AccountType accountType;
}
