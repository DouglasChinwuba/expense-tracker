package com.expensetracker.notification.service;

import com.expensetracker.notification.model.Account;

public interface AccountService{

    Account findByName(String accountName);

    void updateSetting(String username, boolean receiveEmail);
}
