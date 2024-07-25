package dev.lokeshbisht.account_payment_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.account_payment_service.dto.account.AccountsDto;
import dev.lokeshbisht.account_payment_service.dto.request.CreateAccountRequestDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.entity.Account;
import dev.lokeshbisht.account_payment_service.entity.User;
import dev.lokeshbisht.account_payment_service.enums.AccountType;
import dev.lokeshbisht.account_payment_service.exceptions.InternalServerErrorException;
import dev.lokeshbisht.account_payment_service.exceptions.UnauthorizedAccessException;
import dev.lokeshbisht.account_payment_service.repository.AccountRepository;
import dev.lokeshbisht.account_payment_service.repository.UserRepository;
import dev.lokeshbisht.account_payment_service.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public UserAccountInfoDto createAccount(CreateAccountRequestDto createAccountRequestDto) {
        logger.info("Create account for user: {}", createAccountRequestDto.getUserId());
        Optional<User> user = userRepository.findByUserId(createAccountRequestDto.getUserId());
        if (user.isEmpty()) {
            logger.error("User not found.");
            throw new UnauthorizedAccessException("Unauthorized access.");
        }
        Account account = createAccount(createAccountRequestDto, user.get().getUserId());
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccounts(List.of(account));
        String accounts = "";
        try {
            accounts = objectMapper.writeValueAsString(accountsDto);
        } catch (JsonProcessingException ex) {
            logger.error("An error occurred while converting accountsDto to json string");
            throw new InternalServerErrorException("Unexpected error occurred. Please, try again later");
        }
        user.get().setAccounts(accounts);
        User updatedUser = userRepository.save(user.get());
        return prepareCreateAccountResponse(updatedUser, account);
    }

    private Account createAccount(CreateAccountRequestDto createAccountRequestDto, String userId) {
        Account account = new Account();
        account.setBalance(createAccountRequestDto.getBalance());
        account.setAccountType(AccountType.valueOf(createAccountRequestDto.getAccountType().name()));
        account.setCreatedBy(userId);
        account.setCreatedAt(new Date());
        return accountRepository.save(account);
    }

    private UserAccountInfoDto prepareCreateAccountResponse(User user, Account account) {
        return UserAccountInfoDto.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .email(user.getEmail())
            .accounts(List.of(account))
            .build();
    }
}
