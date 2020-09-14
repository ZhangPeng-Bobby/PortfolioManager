package com.citi.group12.sevice;

import com.citi.group12.dao.impl.InvestmentDaoImpl;
import com.citi.group12.dao.impl.ProductDaoImpl;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.PriceType;
import com.citi.group12.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.DoubleToLongFunction;

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

    public void deleteInvestmentById(String id) {
        investmentDao.delete(id);
    }


    public Map<Date, Double> getInvestmentVal(Date startDate, Date endDate) {
        Map<Date, Double> specificDaysInvestmentValue = new HashMap<>();
        List<Date> dates = new DateUtil().getAllDatesBetweenGiven(startDate, endDate);

        for (Date date : dates) {
            specificDaysInvestmentValue.put(date, getSpecificDayInvestmentValue(date));
        }

        return specificDaysInvestmentValue;
    }

    private double getSpecificDayInvestmentValue(Date date) {
        double value = 0.0;
        //todo use stream to optimise this
        List<Investment> investments = investmentDao.findAll();
        for (Investment investment : investments
        ) {
            double closePrice = productDao.findOneBySymbolAndTypeAndDate(investment.getSymbol(), PriceType.CLOSE, date).getPrice();
//            Double closePrice =  Arrays.stream(productDao
//                    .findOneBySymbolAndDate
//                            (investment.getSymbol(), date)
//                    .getPrices())
//                    .filter(e->e.getType()==PriceType.CLOSE)
//                    .findFirst()
//                    .get()
//                    .getPrice();
            value += investment.getShare() * closePrice;
        }
        return value;
    }

}
