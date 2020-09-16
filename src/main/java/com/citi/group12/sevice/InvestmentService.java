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
        Map<String, Double> specificDaysInvestmentValue = new LinkedHashMap<>();
        List<Date> dates = new DateUtil().getAllDatesBetweenGiven(startDate, endDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        log.info("the dates between the given interval is:" + dates);

        List<Investment> investments = investmentDao.findAll();
        for (Date date : dates) {
            specificDaysInvestmentValue.put(sdf.format(date), getSpecificDayInvestmentValue(date, investments));
        }

        return specificDaysInvestmentValue;
    }

    @SneakyThrows
    public double getSpecificDayInvestmentValue(Date date, List<Investment> investments) {
        double value = 0.0;

        for (Investment investment : investments
        ) {
            log.info("date before sdf transfer: " + date);

//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//            log.info("default: "+date);
//            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//            log.info("UTC:  "+date);

//            String time = sdf.format(date);
//            Date magicDate = sdf.parse(time);
//
//            log.info("date after transfer: " + magicDate);
            Product specificDateProduct = productDao.findOneBySymbolAndTypeAndDate(investment.getSymbol(), PriceType.CLOSE, date);

            log.info("getting investment value, the date " + date + " product is " + specificDateProduct);

            double closePrice = investment.getPurchasedPrice();

            if (specificDateProduct != null) {
                closePrice = specificDateProduct.getPrice();
            }

            value += investment.getShare() * closePrice;
        }
        return value;
    }
}
