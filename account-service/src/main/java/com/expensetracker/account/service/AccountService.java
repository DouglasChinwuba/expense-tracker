package com.expensetracker.account.service;

import com.expensetracker.account.model.Account;
import com.expensetracker.account.model.User;

public interface AccountService {

    Account create(User user);
}
