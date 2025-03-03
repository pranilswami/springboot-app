package com.app.controller.crm;

import com.app.entity.evaluation.Agent;
import com.app.entity.evaluation.Area;
import com.app.entity.evaluation.CustomerVisit;
import com.app.repository.evaluation.AgentRepository;
import com.app.repository.evaluation.AreaRepository;
import com.app.repository.evaluation.CustomerVisitRepository;
import com.app.service.evaluation.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crm")
public class CRMController {
    private final AgentRepository agentRepo;
    private final AreaRepository areaRepo;
    private final CustomerVisitRepository customerRepo;
    private final SmsService smsService;

    @Autowired
    public CRMController(AgentRepository agentRepo, AreaRepository areaRepo, CustomerVisitRepository customerRepo, SmsService smsService) {
        this.agentRepo = agentRepo;
        this.areaRepo = areaRepo;
        this.customerRepo = customerRepo;
        this.smsService = smsService;
    }

    @GetMapping("/pinCode")
    public ResponseEntity<List<Area>> searchAgent(@RequestParam int pinCode){
        List<Area> areaList = areaRepo.findByPinCode(pinCode);
        return new ResponseEntity<>(areaList, HttpStatus.OK);
    }
    @PutMapping("/allocateAgent")
    public ResponseEntity<String> updateCustomerVisit(@RequestParam Long customerId,@RequestParam Long agentId){
        CustomerVisit customerVisit = customerRepo.findById(customerId).orElseThrow(()->new RuntimeException("CUSTOMER NOT FOUND WITH GIVEN ID"));
        Agent agent = agentRepo.findById(agentId).orElseThrow(()->new RuntimeException("AGENT NOT FOUND WITH GIVEN ID"));
        customerVisit.setAgent(agent);
        customerRepo.save(customerVisit);
        smsService.sendSms("+918767126108","Congratulations!! Agent is now allocated successfully..");
        return new ResponseEntity<>("AGENT ALLOCATED SUCCESSFULLY !",HttpStatus.OK);
    };
}
