package com.citi.group12.sevice;

import com.citi.group12.dao.impl.InvestmentDaoImpl;
import com.citi.group12.entity.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestmentService {
    private InvestmentDaoImpl investmentDao;

    @Autowired
    public InvestmentService(InvestmentDaoImpl investmentDao) {
        this.investmentDao = investmentDao;
    }

    //there is no return value to represent status of add
    public void addNewInvestment(Investment investment) {
        this.investmentDao.save(investment);
    }

    public void deleteInvestmentById(String id) {
        investmentDao.delete(id);
    }

}
