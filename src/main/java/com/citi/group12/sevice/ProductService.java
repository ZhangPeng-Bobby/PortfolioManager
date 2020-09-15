package com.citi.group12.sevice;

import com.citi.group12.dao.impl.ProductDaoImpl;
import com.citi.group12.entity.PortType;
import com.citi.group12.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Map<String,String> getProductByPortType(String type) {
        List<Product> list = productDao.findByPortType(type);
        log.info("getting products by port type: " + list);
        Map<String,String> result=new HashMap<>();
        for(Product l:list){
            if(!result.containsKey(l.getSymbol())){
                result.put(l.getSymbol(),l.getName());
            }
        }

        return result;
    }
}
