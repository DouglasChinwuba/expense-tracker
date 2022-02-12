package com.expensetracker.account.controller;

import com.expensetracker.account.model.Account;
import com.expensetracker.account.model.User;
import com.expensetracker.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public ResponseEntity<?> getAccountByName(@Valid @RequestBody User user){
        Account account = accountService.findByName(user.getUsername());
//        return account != null ? ResponseEntity.ok(account) : new ResponseEntity(HttpStatus.FORBIDDEN);
        logger.info("Returning account");
        return ResponseEntity.ok(account);
    }

    @PostMapping("/create")
    public Account createNewAccount(@Valid @RequestBody User user){
        logger.info("Creating account");
        Account account = accountService.create(user);

        return account;
    }
}
