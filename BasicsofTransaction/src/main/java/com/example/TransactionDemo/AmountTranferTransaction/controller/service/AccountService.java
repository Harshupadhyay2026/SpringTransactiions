package com.example.TransactionDemo.AmountTranferTransaction.controller.service;

import com.example.TransactionDemo.AmountTranferTransaction.controller.model.Account;
import com.example.TransactionDemo.AmountTranferTransaction.controller.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public void createAccount(Account account){
        accountRepository.save(account);
    }
}
