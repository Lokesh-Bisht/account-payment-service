package dev.lokeshbisht.account_payment_service.service.impl;

import dev.lokeshbisht.account_payment_service.dto.request.CreateAccountRequestDto;
import dev.lokeshbisht.account_payment_service.dto.response.AccountDto;
import dev.lokeshbisht.account_payment_service.dto.response.UserAccountInfoDto;
import dev.lokeshbisht.account_payment_service.entity.Account;
import dev.lokeshbisht.account_payment_service.service.AccountService;
import dev.lokeshbisht.account_payment_service.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    public static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserAccountInfoDto createAccount(CreateAccountRequestDto createAccountRequestDto) {
        return null;
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
