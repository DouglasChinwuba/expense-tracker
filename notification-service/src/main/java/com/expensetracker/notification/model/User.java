package com.expensetracker.notification.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Component
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="id")
    private int id;

    @Column(name = "first_name")
    @NotBlank
    private String firstname;

    @Column(name = "last_name")
    @NotBlank
    private String lastname;

    @Column(name = "user_name")
    @NotBlank
    private String username;

    @Column(name = "email")
    @NotBlank
    @Email
    private String email;

    public User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
