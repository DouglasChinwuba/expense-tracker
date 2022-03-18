package com.expensetracker.notification.model;

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
}
