package dev.lokeshbisht.account_payment_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.account_payment_service.dto.account.AccountIdsDto;
import dev.lokeshbisht.account_payment_service.dto.request.CreateAccountRequestDto;
import dev.lokeshbisht.account_payment_service.dto.response.AccountDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.entity.Account;
import dev.lokeshbisht.account_payment_service.entity.User;
import dev.lokeshbisht.account_payment_service.entity.UserAccount;
import dev.lokeshbisht.account_payment_service.enums.AccountType;
import dev.lokeshbisht.account_payment_service.enums.PermissionsType;
import dev.lokeshbisht.account_payment_service.exceptions.InternalServerErrorException;
import dev.lokeshbisht.account_payment_service.exceptions.UnauthorizedAccessException;
import dev.lokeshbisht.account_payment_service.repository.AccountRepository;
import dev.lokeshbisht.account_payment_service.repository.UserAccountRepository;
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
    private UserAccountRepository userAccountRepository;

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
        AccountIdsDto accountsDto = new AccountIdsDto();
        accountsDto.setAccounts(List.of(account.getId().toString()));
        String accounts = "";
        try {
            accounts = objectMapper.writeValueAsString(accountsDto);
        } catch (JsonProcessingException ex) {
            logger.error("An error occurred while converting accountsDto to json string");
            throw new InternalServerErrorException("Unexpected error occurred. Please, try again later");
        }
        user.get().setAccounts(accounts);
        User updatedUser = userRepository.save(user.get());
        logger.info("Create user permissions");
        createUserAccountPermissions(user.get(), account);
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

    private void createUserAccountPermissions(User user, Account account) {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountId(account.getId());
        userAccount.setUserId(user.getUserId());
        userAccount.setAdmin(true);
        userAccount.setPermissions(PermissionsType.VIEW_AND_PAY_BILL.toString());
        userAccount.setCreatedAt(new Date());
        userAccount.setCreatedBy(user.getUserId());
        userAccountRepository.save(userAccount);
    }

    private UserAccountInfoDto prepareCreateAccountResponse(User user, Account account) {
        return UserAccountInfoDto.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .email(user.getEmail())
            .accounts(List.of(account))
            .build();
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return convertToDto(account);
        }
        return null;
    }

    private AccountDto convertToDto(Account account) {
        AccountDto accountDTO = new AccountDto();
        accountDTO.setId(account.getId());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setCreatedBy(account.getCreatedBy());
        accountDTO.setCreatedAt(account.getCreatedAt());
        accountDTO.setUpdatedBy(account.getUpdatedBy());
        accountDTO.setUpdatedAt(account.getUpdatedAt());
        return accountDTO;
    }
}
