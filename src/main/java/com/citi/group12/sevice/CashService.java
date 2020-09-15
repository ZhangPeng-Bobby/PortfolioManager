package com.citi.group12.sevice;

import com.citi.group12.dao.impl.CashDaoImpl;
import com.citi.group12.entity.Cash;
import com.citi.group12.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
@Service
public class CashService {
    private final CashDaoImpl cashDao;

    @Autowired
    public CashService(CashDaoImpl cashDao) {
        this.cashDao = cashDao;
    }

    public Map<String, Double> getCashVal(Date startDate, Date endDate) {
        Map<String, Double> specificDaysCashValue = new LinkedHashMap<>();

        List<Date> dates = new DateUtil().getAllDatesBetweenGiven(startDate, endDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        log.info("getting cash value, the dates are: " + dates);


        List<Cash> cashList = cashDao.findByDateInterval(startDate, endDate);

        for (int i = 0; i < dates.size(); i++) {
            if (i < cashList.size() && cashList.get(i) != null) {
                specificDaysCashValue.put(sdf.format(dates.get(i)), cashList.get(i).getBalance());
            } else {
                specificDaysCashValue.put(sdf.format(dates.get(i)), 0.0);
            }
        }
        return specificDaysCashValue;
    }
}
