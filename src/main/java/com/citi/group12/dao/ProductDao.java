package com.citi.group12.dao;

import com.citi.group12.entity.Investment;
import com.citi.group12.entity.Product;

import java.util.Date;
import java.util.List;

public interface ProductDao {
    void save(Product product);

    long update(Product product);

    List<Product> findAll();

    Product findOneById(String id);

    Product findOneBySymbolAndDate(String symbol, Date date);

    List<Product> getProductBySymbolAndDateInterval(String symbol, Date startDate, Date endDate);

    void delete(String id);


}
