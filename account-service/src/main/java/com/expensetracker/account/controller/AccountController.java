package com.expensetracker.account.controller;

import com.expensetracker.account.model.Account;
import com.expensetracker.account.model.User;
import com.expensetracker.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{name}")
    public String getAccountByName(String name){
        return "This is my account";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewAccount(@Valid @RequestBody User user){
        Account account = accountService.create(user);
        return ResponseEntity.ok(account);
    }
}
