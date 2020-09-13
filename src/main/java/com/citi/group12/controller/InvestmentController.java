package com.citi.group12.controller;

import com.citi.group12.entity.Investment;
import com.citi.group12.sevice.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/investment")
public class InvestmentController {
    @Autowired //Field injection is not recommended
    private InvestmentService investmentService;

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity addNewInvestment(@RequestBody Investment investment) {
        investmentService.addNewInvestment(investment);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}",produces = {"application/json"})
    public ResponseEntity deleteAnInvestment(@PathVariable String id) {
        investmentService.deleteInvestmentById(id);
        return ResponseEntity.ok().build();
    }
}
