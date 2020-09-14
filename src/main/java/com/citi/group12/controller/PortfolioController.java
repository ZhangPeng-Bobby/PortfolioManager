package com.citi.group12.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.Portfolio;
import com.citi.group12.entity.Product;
import com.citi.group12.sevice.InvestmentService;
import com.citi.group12.sevice.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;

    @Autowired
    InvestmentService investmentService;

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<List<Portfolio>> getAllPortfolio() {
        return ResponseEntity.ok().body(portfolioService.getAllPortfolio());
    }

    @GetMapping(value = "/netVal")
    public Map<Date, Double> getNetValue(@RequestParam Date start, @RequestParam Date end) {
        return null;
    }

    @GetMapping(value = "/cashVal")
    public Map<Date, Double> getCashValue(@RequestParam Date start, @RequestParam Date end) {
        return null;
    }


    @GetMapping(value = "/investmentVal", produces = {"application/json"})
    public Map<Date, Double> getInvestmentValue(@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return investmentService.getInvestmentVal(start, end);
    }


}
