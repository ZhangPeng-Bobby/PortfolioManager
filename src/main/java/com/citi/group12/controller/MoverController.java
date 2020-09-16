package com.citi.group12.controller;

import com.citi.group12.entity.Investment;
import com.citi.group12.entity.Portfolio;
import com.citi.group12.sevice.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/mover")
public class MoverController {
    @Autowired
    PortfolioService portfolioService;



    @GetMapping(value = "/top5Gainer",produces = {"application/json"})
    public ResponseEntity<List<Portfolio>>  getTop5Gainer() {
        return ResponseEntity.ok().body(portfolioService.getTop5Portfolio());
    }

    @GetMapping(value = "/top5Loser", produces = {"application/json"})
    public ResponseEntity<List<Portfolio>>  getTop5Loser() {
        return ResponseEntity.ok().body(portfolioService.getBottom5Portfolio());
    }


}
