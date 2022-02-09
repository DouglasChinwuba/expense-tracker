package com.expensetracker.account.service;

import com.expensetracker.account.model.Account;
import com.expensetracker.account.model.User;
import com.expensetracker.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

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

        accountRepository.save(account);

        logger.info("new account has been created: " + account.getName());

        return account;
    }
}
