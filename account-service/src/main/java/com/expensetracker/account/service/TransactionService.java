package com.expensetracker.account.service;

import com.expensetracker.account.model.Transaction;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);

    Transaction deleteTransaction(Transaction transaction);
}
