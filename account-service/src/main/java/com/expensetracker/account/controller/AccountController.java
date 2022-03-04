package com.expensetracker.account.controller;

import com.expensetracker.account.model.Account;
import com.expensetracker.account.model.Transaction;
import com.expensetracker.account.model.User;
import com.expensetracker.account.service.AccountService;
import com.expensetracker.account.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/account/{username}")
    public ResponseEntity<?> getAccountByName(@Valid @PathVariable String username){
        Account account = accountService.findByName(username);
        return account != null ? ResponseEntity.ok(account) : new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/save/{username}")
    public ResponseEntity<?> saveTransaction(@Valid @RequestBody Transaction transaction){
        transactionService.saveTransaction(transaction);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/delete/{username}")
    public ResponseEntity<?> deleteTransaction(@Valid @RequestBody Transaction transaction){
        transactionService.deleteTransaction(transaction);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/create")
    public Account createNewAccount(@Valid @RequestBody User user){
        logger.info("Creating account");
        Account account = accountService.create(user);
        return account;
    }
}
