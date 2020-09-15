package com.citi.group12.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * created by Alex in 09/12/2020
 *
 * static data part for the portfolio
 */
@Document(collection="Investments")
public class Investment implements Serializable {
    @Id
    private String id;

    private String symbol;

    private String name;

    private PortType type;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date purchasedDate;

    private int share;

    private double purchasedPrice;


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

    public PortType getType() {
        return type;
    }

    public void setType(PortType type) {
        this.type = type;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public double getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
