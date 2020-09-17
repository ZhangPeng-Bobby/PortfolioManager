package com.citi.group12.sevice;

import com.citi.group12.dao.InvestmentDao;
import com.citi.group12.dao.impl.ProductDaoImpl;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.PortType;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class InvestmentServiceTest {
    @Autowired
    InvestmentService investmentService;
    @Autowired
    InvestmentDao investmentDao;
    @Autowired
    ProductDaoImpl productDao;
    Product product1;
    Investment investment1;


    @BeforeEach
    public void setUp() throws ParseException {
        product1 = new Product();
        product1.setSymbol("APPL");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2020-09-13");
        product1.setDate(date);
        product1.setType(PriceType.CLOSE);
        product1.setPrice(101.11);

        investment1=new Investment();
        investment1.setName("Gold Dec 20");
        investment1.setSymbol("GC=F");
        investment1.setType(PortType.FUTURE);
        investment1.setPurchasedDate(date);
        investment1.setShare(10);
        investment1.setPurchasedPrice(1925);
    }

    @Test
    void addNewInvestment() {
        investmentService.addNewInvestment(investment1);
        Investment investment=investmentDao.findOne(investment1.getId());
        assertEquals(investment.getName(),investment1.getName());

    }

    @Test
    void deleteInvestmentById() {
        investmentDao.save(investment1);
        int i=investmentDao.findAll().size();
        investmentService.deleteInvestmentById(investment1.getId());
        assertEquals(1,i-investmentDao.findAll().size());
    }

    @Test
    void getInvestmentVal() {
        productDao.save(product1);
        assertNotNull(investmentService.getInvestmentVal(product1.getDate(),product1.getDate()));
        productDao.delete(product1.getId());
    }

    @Test
    void getAllInvestment() {
        assertNotNull(investmentService.getAllInvestment());
    }


    @Test
    void getSpecificDayInvestmentValue() {
        Double dayInvestmentValue=investmentService.getSpecificDayInvestmentValue(investment1.getPurchasedDate(),investmentDao.findAll());
        assertNotEquals(0,dayInvestmentValue);
        System.out.println(dayInvestmentValue);
    }
}