package com.citi.group12.sevice;

import com.citi.group12.dao.impl.ProductDaoImpl;
import com.citi.group12.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDaoImpl productDao;

    public List<Product> getAllProduct() {
        return productDao.findAll();
    }

    public List<Product> getProductBySymbolAndDateInterval(String symbol, Date startDate, Date endDate) {
        return productDao.getProductBySymbolAndDateInterval(symbol, startDate, endDate);
    }
}
