package com.citi.group12.dao.impl;

import com.citi.group12.dao.ProductDao;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductDaoImplTest {
    @Autowired
    private ProductDao productDao;

    Product product1;


    @BeforeEach
    public void setUp() throws ParseException {
        product1 = new Product();
        product1.setSymbol("TSLA");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2020-08-25");
        product1.setDate(date);
        product1.setType(PriceType.DIVIDEND);
        product1.setPrice(10);

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
        List<Product> products = productDao.findAll();
        assertNotNull(products);
    }

    @Test
    void findBySymbol(){
        List<Product> products = productDao.findBySymbol("APPL");
        assertNotNull(products);
    }

    @Test
    void findOne() {
    }

    @Test
    void delete() {
    }

    @Test
    void findOneBySymbolAndDate() throws ParseException {
        productDao.save(product1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse("2020-09-13");
        List<Product> products = productDao.findBySymbolAndDate(product1.getSymbol(),date);
        assertNotNull(products);
        productDao.delete(product1.getId());
    }

    @Test
    void findOneById() {
    }

    @Test
    void findBySymbolAndDate() {
    }

    @Test
    void findBySymbolAndType() {
    }

    @Test
    void findOneBySymbolAndTypeAndDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d=new Date();
        String time=sdf.format(d);
        Date date = sdf.parse(time);
        Product product=productDao.findOneBySymbolAndTypeAndDate("APPL", PriceType.OPEN,date);
        assertNotNull(product);
    }

    @Test
    void getProductBySymbolAndDateInterval() {
    }
}