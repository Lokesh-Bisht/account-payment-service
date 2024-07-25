package dev.lokeshbisht.account_payment_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.account_payment_service.dto.account.AccountIdsDto;
import dev.lokeshbisht.account_payment_service.dto.response.AccountPermissionDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountPermissionInfoDto;
import dev.lokeshbisht.account_payment_service.entity.Account;
import dev.lokeshbisht.account_payment_service.entity.User;
import dev.lokeshbisht.account_payment_service.entity.UserAccount;
import dev.lokeshbisht.account_payment_service.exceptions.InternalServerErrorException;
import dev.lokeshbisht.account_payment_service.exceptions.UnauthorizedAccessException;
import dev.lokeshbisht.account_payment_service.repository.AccountRepository;
import dev.lokeshbisht.account_payment_service.repository.UserAccountRepository;
import dev.lokeshbisht.account_payment_service.repository.UserRepository;
import dev.lokeshbisht.account_payment_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserAccountPermissionInfoDto findAllAccounts(String userId) {
        logger.info("Start fetching all accounts linked with user: {}", userId);
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            logger.error("User not found.");
            throw new UnauthorizedAccessException("Unauthorized access.");
        }
        List<Long> accountIds = new ArrayList<>();
        try {
            accountIds = objectMapper.readValue(user.get().getAccounts(), AccountIdsDto.class).getAccounts()
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        } catch (JsonProcessingException ex) {
            logger.error("An error occurred while converting jsonString to json accountsDto.");
            throw new InternalServerErrorException("Unexpected error occurred. Please, try again later");
        }
        List<Account> accounts = accountRepository.findAllById(accountIds);
        return createFindAllAccountsResponse(user.get(), accounts);
    }

    private UserAccountPermissionInfoDto createFindAllAccountsResponse(User user, List<Account> accounts) {

        List<AccountPermissionDto> accountPermissionDtoList = new ArrayList<>();
        for (Account account : accounts) {
            UserAccount userAccount = userAccountRepository.findByUserIdAndAccountId(user.getUserId(), account.getId());
            AccountPermissionDto accountPermissionDto = AccountPermissionDto.builder()
                .accountId(account.getId())
                .balance(account.getBalance())
                .accountType(account.getAccountType().toString())
                .isAdmin(userAccount.isAdmin())
                .permissions(userAccount.getPermissions())
                .permissions(userAccount.getPermissions())
                .build();
            accountPermissionDtoList.add(accountPermissionDto);
        }

        return UserAccountPermissionInfoDto.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .email(user.getEmail())
            .accounts(accountPermissionDtoList)
            .build();
    }
}
