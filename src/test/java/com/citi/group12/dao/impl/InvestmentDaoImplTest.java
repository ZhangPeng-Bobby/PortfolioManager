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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class InvestmentDaoImplTest {

    @Autowired
    private InvestmentDao investmentDao;

    Investment investment1;


    @BeforeEach
    void setUp() throws ParseException {
        investment1=new Investment();
        investment1.setName("Gold Dec 20");
        investment1.setSymbol("GC=F");
        investment1.setType(PortType.FUTURE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2020-08-25");
        investment1.setPurchasedDate(date);
        investment1.setShare(10);
        investment1.setPurchasedPrice(1925);
    }

    @Test
    void save() {
        investmentDao.save(investment1);
    }

    @Test
    void update() {
        investmentDao.save(investment1);
        investment1.setShare(100);
        investmentDao.update(investment1);
        assertEquals(100,investmentDao.findOne(investment1.getId()).getShare());
        investmentDao.delete(investment1.getId());
    }

    @Test
    void findAll() {
        List<Investment> investments=investmentDao.findAll();
        assertNotNull(investments);
    }

    @Test
    void findOne() {
        investmentDao.save(investment1);
        assertEquals(10,investmentDao.findOne(investment1.getId()).getShare());
        investmentDao.delete(investment1.getId());
    }

    @Test
    void delete() {
        investmentDao.save(investment1);
        int i=investmentDao.findAll().size();
        investmentDao.delete(investment1.getId());
        assertEquals(1,i-investmentDao.findAll().size());
    }
}