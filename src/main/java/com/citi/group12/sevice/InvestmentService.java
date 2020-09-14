package com.citi.group12.sevice;

import com.citi.group12.dao.impl.InvestmentDaoImpl;
import com.citi.group12.dao.impl.ProductDaoImpl;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.PriceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    //Get everyday investment value between specific date
    public Map<Date, Double> getInvestmentVal(Date startDate, Date endDate) {
        Map<Date, Double> specificDaysInvestmentValue = new HashMap<>();

        //Following is going to get all dates between the given date
        //Calendar used for adding one day to a date
        List<Date> dates = new ArrayList<>();
        Date tempDate = startDate;
        Calendar calendar = Calendar.getInstance();
        do {
            dates.add(tempDate);
            calendar.setTime(tempDate);
            calendar.add(Calendar.DATE, 1);
            tempDate = calendar.getTime();
        } while (tempDate.before(endDate));

        for (Date date : dates) {
            specificDaysInvestmentValue.put(date, getSpecificDayInvestmentValue(date));
        }

        return specificDaysInvestmentValue;
    }

    private Double getSpecificDayInvestmentValue(Date date) {
        double value = 0.0;
        //todo use stream to optimise this
        List<Investment> investments = investmentDao.findAll();
        for (Investment investment : investments
        ) {
            double closePrice=productDao.findOneBySymbolAndTypeAndDate(investment.getSymbol(), PriceType.CLOSE,date).getPrice();
//            Double closePrice =  Arrays.stream(productDao
//                    .findOneBySymbolAndDate
//                            (investment.getSymbol(), date)
//                    .getPrices())
//                    .filter(e->e.getType()==PriceType.CLOSE)
//                    .findFirst()
//                    .get()
//                    .getPrice();
            value += investment.getShare()*closePrice;
        }
        return value;
    }

}
