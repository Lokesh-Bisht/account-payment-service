package dev.lokeshbisht.account_payment_service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountPermissionDto {

    private Long accountId;

    private double balance;

    @JsonProperty("account_type")
    private String accountType;

    private boolean isAdmin;

    private String permissions;

    @JsonProperty("payment_limit")
    private Double paymentLimit;
}
