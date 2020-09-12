package com.citi.group12.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/mover")
public class MoverController {
    @GetMapping(value = "/top5Gainer")
    public Object getTop5Gainer() { //todo the type needs change
        return null;
    }

    @GetMapping(value = "/top5Loser")
    public Object getTop5Loser() { //todo the type needs change
        return null;
    }


}
