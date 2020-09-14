package com.citi.group12.sevice;

import com.citi.group12.dao.impl.ProductDaoImpl;
import com.citi.group12.entity.PriceType;
import com.citi.group12.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class InvestmentServiceTest {
    @Autowired
    InvestmentService investmentService;
    @Autowired
    ProductDaoImpl productDao;
    Product product1;


    @BeforeEach
    public void setUp() throws ParseException {
        product1 = new Product();
        product1.setSymbol("APPL");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2020-09-13");
        product1.setDate(date);
        product1.setType(PriceType.CLOSE);
        product1.setPrice(101.11);

    }

    @Test
    void addNewInvestment() {
    }

    @Test
    void deleteInvestmentById() {
    }

    @Test
    void getInvestmentVal() {
        productDao.save(product1);
        assertNotNull(investmentService.getInvestmentVal(product1.getDate(),product1.getDate()));
        productDao.delete(product1.getId());
    }
}