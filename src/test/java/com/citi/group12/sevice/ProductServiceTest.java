package com.citi.group12.sevice;

import com.citi.group12.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllProduct() {
        List<Product> products=productService.getAllProduct();
        assertNotNull(products);
        System.out.println(products);
    }

    @Test
    void getProductBySymbolAndDateInterval() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<Product> products=productService.getProductBySymbolAndDateInterval("C",sdf.parse("2020-09-11"),sdf.parse("2020-09-13"));
        assertNotNull(products);
        System.out.println(products);
    }

    @Test
    void getProductByPortType() {
        Map<String,String> products=productService.getProductByPortType("CLOSE");
        assertNotNull(products);
        System.out.println(products);
    }
}