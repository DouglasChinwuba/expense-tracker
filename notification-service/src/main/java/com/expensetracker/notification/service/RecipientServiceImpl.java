package com.expensetracker.notification.service;

import com.expensetracker.notification.model.Account;
import com.expensetracker.notification.model.Recipient;
import com.expensetracker.notification.model.User;
import com.expensetracker.notification.repository.AccountRepository;
import com.expensetracker.notification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RecipientServiceImpl {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    List<Recipient> getAllRecipients(){
        List<Account> accounts = accountRepository.findByNotificationEnabled(true);
        return accounts.stream().map(account -> {
            User user = userRepository.findByUsername(account.getName());
            return new Recipient(user.getEmail(), account);
        }).collect(Collectors.toList());
    }
}
