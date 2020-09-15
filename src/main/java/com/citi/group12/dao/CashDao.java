package com.citi.group12.dao;

import com.citi.group12.entity.Cash;

import java.util.Date;
import java.util.List;

public interface CashDao {
    void save(Cash cash);
    long update(Cash cash);
    List<Cash> findAll();
    Cash findOne(String id);
    List<Cash> findByDateInterval(Date startDate, Date endDate);
    void delete(String id);

}
