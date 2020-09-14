package com.citi.group12.sevice;

import com.citi.group12.dao.impl.CashDaoImpl;
import com.citi.group12.entity.Cash;
import com.citi.group12.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class CashService {
    private final CashDaoImpl cashDao;

    @Autowired
    public CashService(CashDaoImpl cashDao) {
        this.cashDao = cashDao;
    }

    public Map<Date, Double> getCashVal(Date startDate, Date endDate) {
        Map<Date, Double> specificDaysCashValue = new HashMap<>();
        List<Date> dates = new DateUtil().getAllDatesBetweenGiven(startDate, endDate);
        log.info("getting cash value, the dates are: " + dates);

        for (Date date : dates) {
            specificDaysCashValue.put(date, getSpecificDayCashValue(date));
        }

        return specificDaysCashValue;
    }

    private double getSpecificDayCashValue(Date date) {
        double value = 0;
        List<Cash> cashList = cashDao.findAll();
        log.info("calculating " + date + " cash value, all cash is " + cashList);
        for (Cash cash : cashList
        ) {
            value += cash.getBalance();
        }

        return value;
    }
}
