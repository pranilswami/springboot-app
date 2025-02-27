package com.app.entity.evaluation;

import jakarta.persistence.*;


@Entity
@Table(name = "agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mobile", nullable = false, length = 10)
    private String mobile;

    @Column(name = "email", nullable = false)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getMobile() {
//        return mobile;
//    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

//    public String getEmail() {
//        return email;
//    }

    public void setEmail(String email) {
        this.email = email;
    }
}