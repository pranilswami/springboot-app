package com.app.payload.evaluation;

import com.app.entity.evaluation.Agent;

public class AreaDto {
    private Integer pinCode;
    private Agent agent;

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
