package com.expensetracker.notification.repository;

import com.expensetracker.notification.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Integer> {

    Account findByName(String name);
}
