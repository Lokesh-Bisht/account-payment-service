package dev.lokeshbisht.account_payment_service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.lokeshbisht.account_payment_service.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@Setter
public class AccountDto {

    private Long id;

    private double balance;

    private AccountType accountType;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;
}
