package com.expensetracker.account.service;

import com.expensetracker.account.model.Account;
import com.expensetracker.account.model.User;
import com.expensetracker.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account findByName(String accountName) {
        return accountRepository.findByName(accountName);
    }

    @Override
    public Account create(User user) {

        Account account = new Account();
        account.setUserId(user.getId());
        account.setName(user.getUsername());

        return account;
    }
}
