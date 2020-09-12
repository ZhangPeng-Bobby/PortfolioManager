package com.citi.group12.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;


/**
 * created by Alex in 09/12/2020
 *
 * dynamic data part for the portfolio
 */
@Document(collection="Products")
public class Product implements Serializable {
    @Id
    private String id;

    private String symbol;
    private Date date;
    private Price[] prices = new Price[2];

    public Price[] getPrices() {
        return prices;
    }

    public void setPrices(Price[] prices) {
        this.prices = prices;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
