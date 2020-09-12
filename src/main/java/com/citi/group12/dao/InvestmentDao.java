package com.citi.group12.dao;

import com.citi.group12.entity.Investment;

import java.util.List;

public interface InvestmentDao {
    void save(Investment investment);

    long update(Investment investment);

    List<Investment> findAll();

    Investment findOne(String id);

    void delete(String id);
}
