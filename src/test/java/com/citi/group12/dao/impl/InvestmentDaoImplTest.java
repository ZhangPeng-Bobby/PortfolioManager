package com.citi.group12.dao.impl;

import com.citi.group12.dao.InvestmentDao;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.PortType;
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
class InvestmentDaoImplTest {

    @Autowired
    private InvestmentDao investmentDao;

    Investment investment1;


    @BeforeEach
    void setUp() {
        investment1=new Investment();
        investment1.setName("APPLE");
        investment1.setSymbol("APPL");
        investment1.setType(PortType.BOND);
        investment1.setPurchasedDate(new Date());
        investment1.setShare(100);
        investment1.setPurchasedPrice(364.10);
        investment1.setExchange("NY Exchange");
    }

    @Test
    void save() {
        investmentDao.save(investment1);
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