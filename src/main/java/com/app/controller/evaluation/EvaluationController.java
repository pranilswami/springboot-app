package com.app.controller.evaluation;

import com.app.payload.evaluation.AgentDto;
import com.app.payload.evaluation.AreaDto;
import com.app.payload.evaluation.CustomerVisitDto;
import com.app.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/addAgent")
    public ResponseEntity<String> addAgent(@RequestBody AgentDto agentDto){
        String status = evaluationService.addAgent(agentDto);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PostMapping("/addArea")
    public ResponseEntity<String> addArea(@RequestBody AreaDto areaDto){
        String status = evaluationService.addArea(areaDto);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @PostMapping("/addCustomerVisit")
    public ResponseEntity<String> addCustomerVisit(@RequestBody CustomerVisitDto customerDto){
        String status = evaluationService.addCustomerVisit(customerDto);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

}
