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
        logger.info("Finding account by name");
        return accountRepository.findByName(accountName);
    }

    @Override
    public Account create(User user) {

        Account account = new Account();
        account.setName(user.getUsername());

        accountRepository.save(account);
        logger.info("new account has been created: " + account.getName());

        return account;
    }

    @Override
    public void saveChanges(Account accountUpdate) {
        Account account = accountRepository.findByName(accountUpdate.getName());

        account.getTransactions().clear();
        account.setTransactions(accountUpdate.getTransactions());
        accountRepository.save(account);

//        accountUpdate.setId(0);
//        accountRepository.save(accountUpdate);
        logger.info("Saving changes to account:{}", accountUpdate.getName());
    }
}
