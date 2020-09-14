package com.citi.group12.dao.impl;

import com.citi.group12.dao.ProductDao;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.PriceType;
import com.citi.group12.entity.Product;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Product product) {
        mongoTemplate.save(product);
    }

    @Override
    public long update(Product product) {
        //修改的条件
        Query query = new Query(Criteria.where("id").is(product.getId()));

        //修改的内容
        Update update = new Update();
        update.set("symbol", product.getSymbol()).set("date", product.getDate())
                .set("type",product.getType()).set("price", product.getPrice());

        //更新查询返回结果集的第一条
        UpdateResult result = mongoTemplate.updateFirst(query, update, Investment.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        return result.getMatchedCount();
    }

    @Override
    public List<Product> findAll() {
        return mongoTemplate.findAll(Product.class);
    }

    @Override
    public Product findOneById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Product.class);
    }

    @Override
    public List<Product> findBySymbol(String symbol) {
        return mongoTemplate.find(new Query(Criteria.where("symbol").is(symbol)), Product.class);
    }


    @Override
    public List<Product> findBySymbolAndDate(String symbol, Date date) {
        Criteria criteria = new Criteria();
        return mongoTemplate.find(new Query(criteria.andOperator(Criteria.where("symbol").is(symbol), Criteria.where("date").gte(date), Criteria.where("date").lte(date))), Product.class);
    }

    @Override
    public List<Product> findBySymbolAndType(String symbol, PriceType type) {
        Criteria criteria = new Criteria();
        return mongoTemplate.find(new Query(criteria.andOperator(Criteria.where("symbol").is(symbol), Criteria.where("type").is(type))),Product.class);
    }

    @Override
    public Product findOneBySymbolAndTypeAndDate(String symbol, PriceType type, Date date) {
        Criteria c1=Criteria.where("symbol").is(symbol);
        Criteria c2=Criteria.where("type").is(type);
        Criteria c3=Criteria.where("date").is(date);
        return mongoTemplate.findOne(new Query(new Criteria().andOperator(c1,c2,c3)),Product.class);

    }

    @Override
    public List<Product> getProductBySymbolAndDateInterval(String symbol, Date startDate, Date endDate) {
        Criteria criteria = new Criteria();
        return mongoTemplate.find(new Query(criteria.andOperator(Criteria.where("symbol").is(symbol), Criteria.where("date").gte(startDate), Criteria.where("date").lte(endDate))), Product.class);
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Product.class);

    }
}
