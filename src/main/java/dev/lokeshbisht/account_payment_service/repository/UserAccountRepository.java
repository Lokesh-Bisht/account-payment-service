package dev.lokeshbisht.account_payment_service.repository;

import dev.lokeshbisht.account_payment_service.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUserIdAndAccountId(String userId, Long accountId);
}