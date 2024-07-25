package dev.lokeshbisht.account_payment_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.account_payment_service.dto.account.AccountsDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.entity.Account;
import dev.lokeshbisht.account_payment_service.entity.User;
import dev.lokeshbisht.account_payment_service.exceptions.InternalServerErrorException;
import dev.lokeshbisht.account_payment_service.exceptions.UnauthorizedAccessException;
import dev.lokeshbisht.account_payment_service.repository.UserRepository;
import dev.lokeshbisht.account_payment_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserAccountInfoDto findAllAccounts(String userId) {
        logger.info("Start fetching all accounts linked with user: {}", userId);
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            logger.error("User not found.");
            throw new UnauthorizedAccessException("Unauthorized access.");
        }
        List<Account> accounts = new ArrayList<>();
        try {
            accounts = objectMapper.readValue(user.get().getAccounts(), AccountsDto.class).getAccounts();
        } catch (JsonProcessingException ex) {
            logger.error("An error occurred while converting jsonString to json accountsDto.");
            throw new InternalServerErrorException("Unexpected error occurred. Please, try again later");
        }
        return createFindAllAccountsResponse(user.get(), accounts);
    }

    private UserAccountInfoDto createFindAllAccountsResponse(User user, List<Account> accounts) {
        return UserAccountInfoDto.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .email(user.getEmail())
            .accounts(accounts)
            .build();
    }
}
