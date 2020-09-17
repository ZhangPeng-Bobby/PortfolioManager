package com.citi.group12.sevice;

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
class CashServiceTest {

    @Autowired
    CashService cashService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCashVal() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Double> list=cashService.getCashVal(sdf.parse("2020-09-11"), sdf.parse("2020-09-13"));
        assertNotNull(list);
        System.out.println(list.toString());
    }
}