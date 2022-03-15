package com.expensetracker.notification.service;

import com.expensetracker.notification.model.Account;
import com.expensetracker.notification.repository.AccountRepository;
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
        logger.info("Finding account {} by name", accountName);
        return accountRepository.findByName(accountName);
    }

    @Override
    public void updateSetting(String username, boolean receiveEmail) {
        Account account = accountRepository.findByName(username);
        account.setNotificationEnabled(receiveEmail);
        accountRepository.save(account);
        logger.info("Updating account {} notification settings", account.getName());
    }
}
