package com.expensetracker.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(name = "user_id")
    @NotNull
    private int userId;

    @Column(name = "user_name")
    @NotNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Income> incomes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Expense> expenses;

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
