package com.citi.group12.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/investment")
public class InvestmentController {
    @PostMapping
    public Object addNewInvestment(@RequestBody Object investment) {
        return null;
    }

    @PostMapping(value = "/{symbol}")
    public boolean deleteAnInvestment(@PathVariable String symbol) {
        return false;
    }
}
