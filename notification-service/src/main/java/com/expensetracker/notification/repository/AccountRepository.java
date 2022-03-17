package com.expensetracker.notification.repository;

import com.expensetracker.notification.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository <Account, Integer> {

    Account findByName(String name);

    @Query("SELECT a FROM Account a WHERE a.notificationEnabled = :notificationEnabled")
    List<Account> findByNotificationEnabled(@Param("notificationEnabled") boolean notificationEnabled);
}
