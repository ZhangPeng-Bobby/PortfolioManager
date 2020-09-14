package com.citi.group12.controller;

import com.citi.group12.entity.Portfolio;
import com.citi.group12.sevice.CashService;
import com.citi.group12.sevice.InvestmentService;
import com.citi.group12.sevice.PortfolioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;

    @Autowired
    InvestmentService investmentService;

    @Autowired
    CashService cashService;

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<List<Portfolio>> getAllPortfolio() {
        return ResponseEntity.ok().body(portfolioService.getAllPortfolio());
    }

    @GetMapping(value = "/netVal", produces = {"application/json"})
    public ResponseEntity<Map<Date, Double>> getNetValue(@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        log.info("the date input is " + start + " and " + end);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/cashVal", produces = {"application/json"})
    public ResponseEntity<Map<Date, Double>> getCashValue(@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        log.info("the date input is " + start + " and " + end);
        return ResponseEntity.ok().body(cashService.getCashVal(start, end));
    }


    @GetMapping(value = "/investmentVal", produces = {"application/json"})
    public ResponseEntity<Map<Date, Double>> getInvestmentValue(@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        log.info("start date is " + start + ", end date is " + end);
        return ResponseEntity.ok().body(investmentService.getInvestmentVal(start, end));
    }


}
