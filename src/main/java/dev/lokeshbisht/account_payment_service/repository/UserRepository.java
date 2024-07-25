package dev.lokeshbisht.account_payment_service.repository;

import dev.lokeshbisht.account_payment_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
