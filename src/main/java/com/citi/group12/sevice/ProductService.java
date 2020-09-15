package com.citi.group12.sevice;

import com.citi.group12.dao.impl.ProductDaoImpl;
import com.citi.group12.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

@Log4j2
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

    public List<Product> getProductByType(String type) {
        List<Product> list = productDao.findByType(type);
        log.info("getting products by type: " + list);
        LinkedHashSet<Product> set = new LinkedHashSet<Product>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }
}
