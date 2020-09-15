package com.citi.group12.sevice;

import com.citi.group12.dao.impl.InvestmentDaoImpl;
import com.citi.group12.dao.impl.ProductDaoImpl;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.PriceType;
import com.citi.group12.entity.Product;
import com.citi.group12.util.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Map<String, Double> getInvestmentVal(Date startDate, Date endDate) {
        Map<String, Double> specificDaysInvestmentValue = new HashMap<>();
        List<Date> dates = new DateUtil().getAllDatesBetweenGiven(startDate, endDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        log.info("the dates between the given interval is:" + dates);
        for (Date date : dates) {
            specificDaysInvestmentValue.put(sdf.format(date), getSpecificDayInvestmentValue(date));
        }

        return specificDaysInvestmentValue;
    }

    @SneakyThrows
    private double getSpecificDayInvestmentValue(Date date) {
        double value = 0.0;

        List<Investment> investments = investmentDao.findAll();
        for (Investment investment : investments
        ) {
            log.info("date before sdf transfer: " + date);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(date);
            Date magicDate = sdf.parse(time);

            log.info("date after transfer: " + magicDate);
//            Product specificDateProduct = getInvestmentCloseProduct(investment);
            Product specificDateProduct = productDao.findOneBySymbolAndTypeAndDate(investment.getSymbol(), PriceType.CLOSE, magicDate);

            log.info("getting investment value, the date " + date + " product is " + specificDateProduct);

            double closePrice = 0;

            if (specificDateProduct != null) {
                closePrice = specificDateProduct.getPrice();
            }

            value += investment.getShare() * closePrice;
        }
        return value;
    }

    private Product getInvestmentCloseProduct(Investment investment) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String time = sdf.format(d);
        Date date = sdf.parse(time);
        //Date date = sdf.parse("2020-09-14");
        return productDao.findOneBySymbolAndTypeAndDate(investment.getSymbol(), PriceType.CLOSE, date);
    }
}
