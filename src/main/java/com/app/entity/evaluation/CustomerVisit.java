package com.app.entity.evaluation;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "customer_visit")
public class CustomerVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mobile", nullable = false, length = 10)
    private String mobile;

    @Column(name = "pin_code", nullable = false, length = 6)
    private Integer pinCode;

    @Column(name = "date_of_visit")
    private LocalDate dateOfVisit;

    @Column(name = "time_of_visit")
    private LocalTime timeOfVisit;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

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
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public Integer getPinCode() {
//        return pinCode;
//    }
//
//    public void setPinCode(Integer pinCode) {
//        this.pinCode = pinCode;
//    }
//
//    public LocalDate getDateOfVisit() {
//        return dateOfVisit;
//    }
//
//    public void setDateOfVisit(LocalDate dateOfVisit) {
//        this.dateOfVisit = dateOfVisit;
//    }
//
//    public LocalTime getTimeOfVisit() {
//        return timeOfVisit;
//    }
//
//    public void setTimeOfVisit(LocalTime timeOfVisit) {
//        this.timeOfVisit = timeOfVisit;
//    }
//
//    public Agent getAgent() {
//        return agent;
//    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}