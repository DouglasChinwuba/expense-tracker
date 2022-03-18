package com.expensetracker.notification.model;

import java.math.BigDecimal;

public class Recipient {

    private String email;

    private Account account;

    public Recipient(String email, Account account){
        this.email = email;
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
