package com.citi.group12.controller;

import com.citi.group12.entity.Product;
import com.citi.group12.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<List<Product>> getAllPortfolio() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

}
