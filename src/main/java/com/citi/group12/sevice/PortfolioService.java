package com.citi.group12.sevice;

import com.citi.group12.dao.ProductDao;
import com.citi.group12.dao.impl.InvestmentDaoImpl;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.Portfolio;
import com.citi.group12.entity.PriceType;
import com.citi.group12.entity.Product;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PortfolioService {
    @Autowired
    InvestmentDaoImpl investmentDao;

    @Autowired
    ProductDao productDao;

    public List<Investment> getAllInvestment(){
        return investmentDao.findAll();
    }
    @SneakyThrows
    public List<Portfolio> getAllPortfolio(){
        List<Portfolio> portfolios=new ArrayList<>();
        List<Investment> investments= investmentDao.findAll();
        for(Investment i:investments){
            Portfolio portfolio=new Portfolio(i);
            portfolio.setCost(i.getPurchasedPrice()*i.getShare());
            Product p=getInvestmentLiveValue(i);
            if(p!=null){
                portfolio.setCurrentPrice(p.getPrice());
            }else {
                break;
            }
            portfolio.setCurrentValue(portfolio.getCurrentPrice()*portfolio.getShares());
            List<Product> dividends=getStockDividendValue(i);
            double income=0;
            for(Product d:dividends) {
                if (d.getDate().after(portfolio.getPurchaseDate())) {
                    income += d.getPrice() * portfolio.getShares();
                }
            }
            portfolio.setTotalIncome(income);
            portfolio.setNetVal(portfolio.getCurrentValue()+income);
            portfolio.setGain(portfolio.getNetVal()-portfolio.getCost());
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMaximumFractionDigits(1);
            portfolio.setGainp(nf.format(portfolio.getGain()/portfolio.getNetVal()));
            portfolios.add(portfolio);
        }

        return portfolios;
    }


    public Product getInvestmentLiveValue(Investment investment) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d=new Date();
        String time=sdf.format(d);
        Date date = sdf.parse(time);
        return productDao.findOneBySymbolAndTypeAndDate(investment.getSymbol(), PriceType.OPEN,date);
    }

    public List<Product> getStockDividendValue(Investment investment) {
        return productDao.findBySymbolAndType(investment.getSymbol(), PriceType.DIVIDEND);
    }


}
