package com.expensetracker.account.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetail {

    private int id;

    private String username;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetail(int id, String username, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

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

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
