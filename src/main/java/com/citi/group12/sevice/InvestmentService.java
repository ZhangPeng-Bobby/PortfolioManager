package com.citi.group12.sevice;

import com.citi.group12.dao.impl.InvestmentDaoImpl;
import com.citi.group12.dao.impl.ProductDaoImpl;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.PriceType;
import com.citi.group12.entity.Product;
import com.citi.group12.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class InvestmentService {
    private final InvestmentDaoImpl investmentDao;
    private final ProductDaoImpl productDao;

    @Autowired
    public InvestmentService(InvestmentDaoImpl investmentDao, ProductDaoImpl productDao) {
        this.investmentDao = investmentDao;
        this.productDao = productDao;
    }

    //there is no return value to represent status of add
    public void addNewInvestment(Investment investment) {
        this.investmentDao.save(investment);
    }

    public List<Investment> getAllInvestment() {
        return investmentDao.findAll();
    }

    public void deleteInvestmentById(String id) {
        investmentDao.delete(id);
    }


    public Map<Date, Double> getInvestmentVal(Date startDate, Date endDate) {
        Map<Date, Double> specificDaysInvestmentValue = new HashMap<>();
        List<Date> dates = new DateUtil().getAllDatesBetweenGiven(startDate, endDate);
        log.info("the dates between the given interval is:" + dates);
        for (Date date : dates) {
            specificDaysInvestmentValue.put(date, getSpecificDayInvestmentValue(date));
        }

        return specificDaysInvestmentValue;
    }

    private double getSpecificDayInvestmentValue(Date date) {
        double value = 0.0;

        List<Investment> investments = investmentDao.findAll();
        for (Investment investment : investments
        ) {
            Product specificDateProduct = productDao.findOneBySymbolAndTypeAndDate(investment.getSymbol(), PriceType.CLOSE, date);
            log.info("getting investment value, the date "+ date + " product is "+ specificDateProduct);

            double closePrice = 0;

            if (specificDateProduct != null) {
                closePrice = specificDateProduct.getPrice();
            }

            value += investment.getShare() * closePrice;
        }
        return value;
    }

}
