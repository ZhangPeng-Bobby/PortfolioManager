package com.citi.group12.dao;

import com.citi.group12.entity.Investment;
import com.citi.group12.entity.PortType;

import java.util.List;

public interface InvestmentDao {
    void save(Investment investment);

    long update(Investment investment);

    List<Investment> findAll();

    Investment findOne(String id);

    List<Investment> findByType(PortType type);

    void delete(String id);
}
