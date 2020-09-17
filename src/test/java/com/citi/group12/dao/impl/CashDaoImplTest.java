package com.citi.group12.dao.impl;

import com.citi.group12.entity.Cash;
import org.junit.jupiter.api.AfterEach;
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
class CashDaoImplTest {
    @Autowired
    private CashDaoImpl cashDao;

    Cash cash;

    @BeforeEach
    void setCash(){
        cash=new Cash();
        cash.setBalance(504.5);
        cash.setName("bankSaving");
        cash.setType("checking");
        cash.setDate(new Date());
    }

    @Test
    void save() {
        cashDao.save(cash);
    }

    @Test
    void update() {
        cashDao.save(cash);
        cash.setBalance(100);
        cashDao.update(cash);
        assertEquals(100,cashDao.findOne(cash.getId()).getBalance());
        cashDao.delete(cash.getId());
    }

    @Test
    void findAll() {
        assertNotNull(cashDao.findAll());
    }

    @Test
    void findOne() {
        cashDao.save(cash);
        assertNotNull(cashDao.findOne(cash.getId()));
        cashDao.delete(cash.getId());
    }

    @Test
    void delete() {
        cashDao.save(cash);
        int i=cashDao.findAll().size();
        cashDao.delete(cash.getId());
        assertEquals(1,i-cashDao.findAll().size());
    }
}