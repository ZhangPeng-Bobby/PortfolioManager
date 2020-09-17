package com.citi.group12.dao.impl;

import com.citi.group12.dao.ProductDao;
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
        Date date = sdf.parse("2020-09-15");
        product1.setDate(date);
        product1.setPortType(PortType.STOCK);
        product1.setType(PriceType.DIVIDEND);
        product1.setPrice(1705);

    }

    @Test
    public void save() {
        productDao.save(product1);
    }

    @Test
    void update() {
        productDao.save(product1);
        product1.setPrice(100);
        productDao.update(product1);
        assertEquals(100,productDao.findOneById(product1.getId()).getPrice());
        productDao.delete(product1.getId());
    }

    @Test
    void findAll() {
        List<Product> products = productDao.findAll();
        assertNotNull(products);
    }

    @Test
    void findBySymbol(){
        List<Product> products = productDao.findBySymbol("C");
        assertNotNull(products);
    }


    @Test
    void delete() {
        productDao.save(product1);
        int i=productDao.findAll().size();
        productDao.delete(product1.getId());
        assertEquals(1,i-productDao.findAll().size());
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
        productDao.save(product1);
        Product product=productDao.findOneById(product1.getId());
        assertNotNull(product);
    }

    @Test
    void findBySymbolAndDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Product> products=productDao.findBySymbolAndDate("C",sdf.parse("2020-09-10"));
        assertNotNull(products);
    }

    @Test
    void findBySymbolAndType() {
        List<Product> products=productDao.findBySymbolAndType("C",PriceType.OPEN);
        assertNotNull(products);
    }

    @Test
    void findOneBySymbolAndTypeAndDate() throws ParseException {
        productDao.save(product1);
        Product product=productDao.findOneBySymbolAndTypeAndDate(product1.getSymbol(), product1.getType(),product1.getDate());
        assertNotNull(product);
    }

    @Test
    void getProductBySymbolAndDateInterval() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<Product> products=productDao.getProductBySymbolAndDateInterval("C",sdf.parse("2020-09-11"),sdf.parse("2020-09-13"));
        assertNotNull(products);
    }

    @Test
    void findByType() {
        List<Product> products=productDao.findByPortType(PriceType.OPEN.toString());
        assertNotNull(products);
    }

    @Test
    void findByPortType() {
        List<Product> products=productDao.findByPortType(PortType.STOCK.toString());
        assertNotNull(products);
    }
}