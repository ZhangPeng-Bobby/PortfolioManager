package com.citi.group12.dao.impl;

import com.citi.group12.dao.InvestmentDao;
import com.citi.group12.dao.ProductDao;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.Price;
import com.citi.group12.entity.PriceType;
import com.citi.group12.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductDaoImplTest {
    @Autowired
    private ProductDao productDao;

    Product product1;




    @BeforeEach
    public void setUp(){
        product1=new Product();
        product1.setSymbol("APPL");
        product1.setDate(new Date());
        Price priceO=new Price(PriceType.OPEN,300);
        Price priceC=new Price(PriceType.CLOSE,309);
        Price[] prices=new Price[2];
        prices[0]=priceO;
        prices[1]=priceC;
        product1.setPrices(prices);

    }

    @Test
    public void save() {
        productDao.save(product1);
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }

    @Test
    void delete() {
    }
}