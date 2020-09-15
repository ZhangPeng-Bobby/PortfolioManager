package com.citi.group12.controller;

import com.citi.group12.entity.Product;
import com.citi.group12.sevice.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping(value = "/{type}", produces = {"application/json"})
    public ResponseEntity<List<Product>> getProductsByType(@PathVariable String type) {
        log.info(type + "is the type you want to get");
        if(type != null){
            return ResponseEntity.ok().body(productService.getProductByType(type));
        }
        return ResponseEntity.badRequest().build();
    }



}
