package com.citi.group12.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolio")
public class PortfolioController {
    @GetMapping(value = "/all")
    public List<String> getAllPortfolio() { //todo the type needs change
        return null;
    }

    @GetMapping(value = "/netVal")
    public Map<Date, Double> getNetValue(@RequestParam Date start, @RequestParam Date end) {
        return null;
    }

    @GetMapping(value = "/cashVal")
    public Map<Date, Double> getCashValue(@RequestParam Date start, @RequestParam Date end) {
        return null;
    }


    @GetMapping(value = "/investmentVal")
    public Map<Date, Double> getInvestmentValue(@RequestParam Date start, @RequestParam Date end) {
        return null;
    }


}
