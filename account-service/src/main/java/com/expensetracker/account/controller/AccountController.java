package com.expensetracker.account.controller;

import com.expensetracker.account.model.Account;
import com.expensetracker.account.model.User;
import com.expensetracker.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{name}")
    public String getAccountByName(String name){
        return "This is my account";
    }

    @PostMapping("/")
    public Account createNewAccount(@Valid @RequestBody User user){
        return accountService.create(user);
    }
}
