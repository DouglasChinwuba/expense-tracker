package com.expensetracker.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @NotNull
    private int id;

    @NotNull
    private String username;

    @NotNull
    private Collection<?> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<?> getRoles() {
        return roles;
    }

    public void setRoles(Collection<?> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
