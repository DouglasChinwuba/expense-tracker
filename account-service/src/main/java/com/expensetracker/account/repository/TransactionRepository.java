package com.expensetracker.account.repository;

import com.expensetracker.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findById(int id);

    void deleteById(Integer integer);
}
