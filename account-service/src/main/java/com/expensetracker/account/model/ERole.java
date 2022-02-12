package com.expensetracker.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum ERole {
    ROLE_USER,
    ROLE_ADMIN
}
