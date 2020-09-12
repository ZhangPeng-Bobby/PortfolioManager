package com.citi.group12.dao;

import com.citi.group12.entity.Investment;
import com.citi.group12.entity.Product;

import java.util.List;

public interface ProductDao {
    void save(Product product);

    long update(Product product);

    List<Product> findAll();

    Product findOne(String id);

    void delete(String id);


}
