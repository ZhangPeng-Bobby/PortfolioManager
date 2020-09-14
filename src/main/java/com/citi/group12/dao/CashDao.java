package com.citi.group12.dao;

import com.citi.group12.entity.Cash;

import java.util.List;

public interface CashDao {
    void save(Cash cash);
    long update(Cash cash);
    List<Cash> findAll();
    Cash findOne(String id);
    void delete(String id);
}
