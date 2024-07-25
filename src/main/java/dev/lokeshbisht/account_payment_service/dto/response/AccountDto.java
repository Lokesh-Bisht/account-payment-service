package dev.lokeshbisht.account_payment_service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.lokeshbisht.account_payment_service.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdAt;

    private String updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updatedAt;
}
