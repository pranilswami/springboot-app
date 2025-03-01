package com.app.payload.evaluation;

import com.app.entity.evaluation.Agent;

public class AreaDto {
    private Integer pinCode;
    private Long agent;

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public Long getAgent() {
        return agent;
    }

    public void setAgent(Long agent) {
        this.agent = agent;
    }
}
