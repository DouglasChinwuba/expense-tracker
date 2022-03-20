package com.expensetracker.notification.service;

import com.expensetracker.notification.model.Account;
import com.expensetracker.notification.model.Recipient;
import com.expensetracker.notification.model.User;
import com.expensetracker.notification.repository.AccountRepository;
import com.expensetracker.notification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipientServiceImpl implements RecipientService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Recipient> findAllRecipients(){
        List<Account> accounts = accountRepository.findByNotificationEnabled(true);
        return accounts.stream().map(account -> {
            User user = userRepository.findByUsername(account.getName());
            return new Recipient(user, account);
        }).collect(Collectors.toList());
    }
}
