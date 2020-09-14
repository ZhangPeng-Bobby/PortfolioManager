package com.citi.group12.dao.impl;

import com.citi.group12.entity.Cash;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    }

    @Test
    void save() {
        cashDao.save(cash);
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
        assertNotNull(cashDao.findAll());
    }

    @Test
    void findOne() {
        assertNotNull(cashDao.findOne("5f5f12c1c5103c69fa020811"));
    }

    @Test
    void delete() {
    }
}