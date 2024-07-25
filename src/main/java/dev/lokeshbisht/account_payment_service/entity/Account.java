package dev.lokeshbisht.account_payment_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.lokeshbisht.account_payment_service.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import static dev.lokeshbisht.account_payment_service.constants.JsonConstants.ISO8601;

@Getter
@Setter
@Entity
@Table(name = "accounts")
@ToString
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1, initialValue = 21345678)
    private Long id;

    private double balance;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO8601)
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO8601)
    private Date updatedAt;
}
