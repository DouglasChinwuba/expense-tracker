package com.expensetracker.account.repository;

import com.expensetracker.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByName(String name);
}
