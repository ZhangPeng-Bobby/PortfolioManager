package com.citi.group12.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    public List<Date> getAllDatesBetweenGiven(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        Date tempDate = startDate;
        //Calendar used for adding one day to a date
        Calendar calendar = Calendar.getInstance();
        do {
            dates.add(tempDate);
            calendar.setTime(tempDate);
            calendar.add(Calendar.DATE, 1);
            tempDate = calendar.getTime();
        } while (!tempDate.after(endDate));

        return dates;
    }
}
