package com.citi.group12.sevice;

import com.citi.group12.dao.impl.InvestmentDaoImpl;
import com.citi.group12.entity.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {
    @Autowired
    InvestmentDaoImpl investmentDao;

    public List<Investment> getAllInvestment(){
        return investmentDao.findAll();
    }
}
