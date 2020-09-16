package com.citi.group12.sevice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.group12.dao.ProductDao;
import com.citi.group12.dao.impl.InvestmentDaoImpl;
import com.citi.group12.entity.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Log4j2
public class PortfolioService {
    @Autowired
    InvestmentDaoImpl investmentDao;

    @Autowired
    InvestmentService investmentService;

    @Autowired
    ProductDao productDao;

    @SneakyThrows
    public List<Portfolio> getAllPortfolio() {
        List<Portfolio> portfolios = new ArrayList<>();
        List<Investment> investments = investmentDao.findAll();
        log.info("getting all portfolio, the investments size:" + investments.size());
        for (Investment i : investments) {
            Portfolio portfolio = new Portfolio(i);
            portfolio.setCost(i.getPurchasedPrice() * i.getShare());
            Product p = getInvestmentLiveValue(i);
            if (p != null) {
                portfolio.setCurrentPrice(p.getPrice());
            } else {
                portfolios.add(portfolio);
                continue;
            }
            portfolio.setCurrentValue(portfolio.getCurrentPrice() * portfolio.getShares());
            List<Product> dividends = getStockDividendValue(i);
            double income = 0;
            for (Product d : dividends) {
                if (d.getDate().after(portfolio.getPurchaseDate())) {
                    income += d.getPrice() * portfolio.getShares();
                }
            }
            portfolio.setTotalIncome(income);
            portfolio.setNetVal(portfolio.getCurrentValue() + income);
            portfolio.setGain(portfolio.getNetVal() - portfolio.getCost());
            portfolio.setGainp(portfolio.getGain() / portfolio.getNetVal());
            portfolios.add(portfolio);
            log.info("they are : " + portfolio);
        }
        log.info(portfolios);
        return portfolios;

    }

    public List<Portfolio> getTop5Portfolio() {
        List<Portfolio> portfolioList = getAllPortfolio();
        Collections.sort(portfolioList, new sortTop());
        int end = Math.min(5, portfolioList.size());
        List<Portfolio> result = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            if (portfolioList.get(i).getGainp() > 0) {
                result.add(portfolioList.get(i));
            }
        }
        return result;
    }

    public List<Portfolio> getBottom5Portfolio() {
        List<Portfolio> portfolioList = getAllPortfolio();
        Collections.sort(portfolioList, new sortBottom());
        int end = Math.min(5, portfolioList.size());
        List<Portfolio> result = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            if (portfolioList.get(i).getGainp() < 0) {
                result.add(portfolioList.get(i));
            }
        }

        return result;
    }


    public Product getInvestmentLiveValue(Investment investment) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String time = sdf.format(d);
        Date date = sdf.parse(time);
        //Date date = sdf.parse("2020-09-14");
        return productDao.findOneBySymbolAndTypeAndDate(investment.getSymbol(), PriceType.OPEN, date);
    }

    public List<Product> getStockDividendValue(Investment investment) {
        return productDao.findBySymbolAndType(investment.getSymbol(), PriceType.DIVIDEND);
    }

    @SneakyThrows
    public JSONArray getTypeValues() {
        JSONArray jsonArray = new JSONArray();
        List<Investment> investments = investmentDao.findAll();

        Map<Enum<PortType>, List<Investment>> groupedInvestment = new HashMap<>();
        Set<Map.Entry<Enum<PortType>, List<Investment>>> entriesOfGroupedInvestment = groupedInvestment.entrySet();

        Map<Enum<PortType>, Double> typeAndValue = new HashMap<>();
        Set<Map.Entry<Enum<PortType>, Double>> entriesOfTypeAndValue = typeAndValue.entrySet();

        for (Investment investment : investments
        ) {
            //Following is to group the investments, if there's a better way going through Enum, optimise this
            switch (investment.getType()) {
                case BOND:
                    this.groupInvestment(groupedInvestment, investment, PortType.BOND);
                    break;
                case STOCK:
                    this.groupInvestment(groupedInvestment, investment, PortType.STOCK);
                    break;
                case FUTURE:
                    this.groupInvestment(groupedInvestment, investment, PortType.FUTURE);
                    break;
                case ETF:
                    this.groupInvestment(groupedInvestment, investment, PortType.ETF);
                    break;
                default:
                    log.info("unknown type of " + investment.getType());
            }
        }

        for (Map.Entry<Enum<PortType>, List<Investment>> entry : entriesOfGroupedInvestment) {
            log.info(entry.getKey() + ":" + entry.getValue());
//            typeAndValue.put(entry.getKey(), investmentService.getSpecificDayInvestmentValue(new Date(), entry.getValue()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2020-09-13"); //todo just demo, should create a current date
            typeAndValue.put(entry.getKey(), investmentService.getSpecificDayInvestmentValue(date, entry.getValue()));
        }


        for (Map.Entry<Enum<PortType>, Double> entry : entriesOfTypeAndValue
        ) {
            JSONObject json = new JSONObject();
            json.put("name", entry.getKey());
            json.put("value", entry.getValue());
            jsonArray.add(json);
        }


        return jsonArray;
    }

    private void groupInvestment(Map<Enum<PortType>, List<Investment>> groupedInvestment, Investment toBeGroup, PortType type) {
        if (groupedInvestment.get(type) != null) {
            groupedInvestment.get(type).add(toBeGroup);
        } else {
            List<Investment> group = new ArrayList<Investment>();
            group.add(toBeGroup);
            groupedInvestment.put(type, group);
        }
    }

}

class sortTop implements Comparator {

    public int compare(Object o1, Object o2) {
        if (((Portfolio) o1).getGainp() > ((Portfolio) o2).getGainp())
            return -1;
        return 1;
    }

}

class sortBottom implements Comparator {

    public int compare(Object o1, Object o2) {
        if (((Portfolio) o1).getGainp() > ((Portfolio) o2).getGainp())
            return 1;
        return -1;
    }

}