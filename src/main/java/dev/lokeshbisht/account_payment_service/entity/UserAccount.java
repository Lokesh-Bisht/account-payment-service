package dev.lokeshbisht.account_payment_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static dev.lokeshbisht.account_payment_service.constants.JsonConstants.ISO8601;

@Entity
@Table(name = "users_accounts")
@Getter
@Setter
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_account_sequence")
    @SequenceGenerator(name = "user_account_sequence", sequenceName = "user_account_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "is_admin")
    private boolean isAdmin;

    private String permissions;

    @Column(name = "payment_limit")
    private Double paymentLimit;

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
