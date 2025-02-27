package com.app.service;

import com.app.entity.evaluation.Agent;
import com.app.entity.evaluation.Area;
import com.app.payload.evaluation.AgentDto;
import com.app.payload.evaluation.AreaDto;
import com.app.payload.evaluation.CustomerVisitDto;
import com.app.repository.evaluation.AgentRepository;
import com.app.repository.evaluation.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvaluationService {
    private final AgentRepository agentRepo;
    private final AreaRepository areaRepo;

    @Autowired
    public EvaluationService(AgentRepository agentRepo, AreaRepository areaRepo) {
        this.agentRepo = agentRepo;
        this.areaRepo = areaRepo;
    }

    public String addAgent(AgentDto agentDto){
        Optional<Agent> opAgent = agentRepo.findByAgentName(agentDto.getName());
        if(opAgent.isPresent()){
            throw new RuntimeException("AGENT WITH GIVEN NAME IS ALREADY EXISTS");
        }
        Agent agent = new Agent();
        agent.setName(agentDto.getName());
        agent.setEmail(agentDto.getEmail());
        agent.setMobile(agentDto.getMobile());
        agentRepo.save(agent);
        return "Agent added successfully";
    }

    public String addArea(AreaDto areaDto){
        Optional<Area> opArea = areaRepo.existsByPinCode(areaDto.getPinCode());
        if(opArea.isPresent()){
            throw new RuntimeException(("AREA WITH GIVEN PIN-CODE IS ALREADY EXISTS"));
        }
        Area area = new Area();
        area.setPincode(areaDto.getPinCode());
        area.setAgent(areaDto.getAgent());
        areaRepo.save(area);
        return "Area added successfully";
    }

    public String addCustomerVisit(CustomerVisitDto customerDto) {
        return "hi";
    }
}
