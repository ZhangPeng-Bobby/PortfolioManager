package com.citi.group12.entity;

public class Price {
    private PriceType type;
    private double price;

    public Price(PriceType type,double price){
        this.type=type;
        this.price=price;
    }

    public PriceType getType() {
        return type;
    }

    public void setType(PriceType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
