package com.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "password", nullable = false, unique = true, length = 1000)
    private String password;

    @Column(name = "mobile", nullable = false, unique = true, length = 10)
    private String mobile;

    @Column(name = "role", nullable = false, length = 25)
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
}