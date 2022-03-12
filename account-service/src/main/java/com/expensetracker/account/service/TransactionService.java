package com.expensetracker.account.service;

import com.expensetracker.account.model.Transaction;

public interface TransactionService {
    Transaction saveTransaction(String username, Transaction transaction);

    void deleteTransaction(int transactionId);
}
