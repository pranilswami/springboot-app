package com.app.entity.evaluation;

import jakarta.persistence.*;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pin_code", nullable = false, length = 6)
    private Integer pinCode;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Integer getPinCode() {
//        return pinCode;
//    }

    public void setPincode(Integer pincode) {
        this.pinCode = pincode;
    }

//    public Agent getAgent() {
//        return agent;
//    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}