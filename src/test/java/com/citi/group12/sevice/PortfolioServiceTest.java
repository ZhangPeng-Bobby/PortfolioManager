package com.citi.group12.sevice;

import com.alibaba.fastjson.JSONArray;
import com.citi.group12.dao.InvestmentDao;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.Portfolio;
import com.citi.group12.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PortfolioServiceTest {
    @Autowired
    PortfolioService portfolioService;

    @Autowired
    InvestmentDao investmentDao;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllPortfolio() {
        List<Portfolio> portfolios=portfolioService.getAllPortfolio();
        assertEquals(investmentDao.findAll().size(),portfolios.size());
    }

    @Test
    void getTop5Portfolio() {
        List<Portfolio> portfolios=portfolioService.getTop5Portfolio();
        assertNotNull(portfolios);
        System.out.println(portfolios);
    }

    @Test
    void getBottom5Portfolio() {
        List<Portfolio> portfolios=portfolioService.getBottom5Portfolio();
        assertNotNull(portfolios);
        System.out.println(portfolios);
    }

    @Test
    void getInvestmentLiveValue() throws ParseException {
        List<Investment> list=investmentDao.findAll();
        Product product =portfolioService.getInvestmentLiveValue(list.get(5));
        assertNotNull(product);
        System.out.println(product);
    }

    @Test
    void getStockDividendValue() {
        List<Investment> list=investmentDao.findAll();
        List<Product> product =portfolioService.getStockDividendValue(list.get(0));
        assertNotNull(product);
        System.out.println(product);
    }


    @Test
    void getCashAndInvestmentValues() {
        JSONArray js=portfolioService.getCashAndInvestmentValues();
        assertNotNull(js);
        System.out.println(js);


    }

    @Test
    void getTypeValues() {
        JSONArray js=portfolioService.getTypeValues();
        assertNotNull(js);
        System.out.println(js);
    }
}