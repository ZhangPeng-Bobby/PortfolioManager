package com.citi.group12.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Portfolio {
    private String id;
    private String symbol;
    private String name;
    private PortType type;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date purchaseDate;

    private int shares;
    private double purchasePrice;

    private double cost;
    private double currentPrice;
    private double currentValue;
    private double totalIncome;

    private double netVal;
    private double gain;

    @JsonIgnore
    private double gainPercent;

    private String gainp;

    public Portfolio(Investment investment){
        this.id=investment.getId();
        this.name=investment.getName();
        this.symbol=investment.getSymbol();
        this.purchaseDate=investment.getPurchasedDate();
        this.purchasePrice=investment.getPurchasedPrice();
        this.shares=investment.getShare();
        this.type=investment.getType();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PortType getType() {
        return type;
    }

    public void setType(PortType type) {
        this.type = type;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getNetVal() {
        return netVal;
    }

    public void setNetVal(double netVal) {
        this.netVal = netVal;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public String getGainp() {
        return gainp;
    }

    public void setGainp(String gainp) {
        this.gainp = gainp;
    }

    public double getGainPercent() {
        return gainPercent;
    }

    public void setGainPercent(double gainPercent) {
        this.gainPercent = gainPercent;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id='" + id + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", purchaseDate=" + purchaseDate +
                ", shares=" + shares +
                ", purchasePrice=" + purchasePrice +
                ", cost=" + cost +
                ", currentPrice=" + currentPrice +
                ", currentValue=" + currentValue +
                ", totalIncome=" + totalIncome +
                ", netVal=" + netVal +
                ", gain=" + gain +
                ", gainp=" + gainp +
                '}';
    }


}
