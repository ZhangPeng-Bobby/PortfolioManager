package com.citi.group12.controller;

import com.citi.group12.entity.Investment;
import com.citi.group12.entity.Product;
import com.citi.group12.sevice.InvestmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/investment")
public class InvestmentController {
    @Autowired //Field injection is not recommended
    private InvestmentService investmentService;

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity addNewInvestment(@RequestBody Investment investment) {
        if(investment.getPurchasedDate()==null){
            return ResponseEntity.status(406).build();
        }
        investmentService.addNewInvestment(investment);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<Collection<Investment>> getAllInvestment() {
        List<Investment> investments = investmentService.getAllInvestment();
        return ResponseEntity.ok().body(investments);
    }

    @PostMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity deleteAnInvestment(@PathVariable String id) {
        investmentService.deleteInvestmentById(id);
        return ResponseEntity.ok().build();
    }
}
