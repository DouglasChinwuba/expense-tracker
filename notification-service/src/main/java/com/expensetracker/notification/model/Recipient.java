package com.expensetracker.notification.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Recipient {

    private User user;

    private Account account;

    public Recipient(User user, Account account){
        this.user = user;
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getBalance(){
        return getIncome().subtract(getExpense());
    }

    public BigDecimal getIncome(){
        return this.account.getTransactions().stream()
                .filter(transaction -> transaction.getType().equals("Income"))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getExpense(){
        return this.account.getTransactions().stream()
                .filter(transaction -> transaction.getType().equals("Expense"))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
