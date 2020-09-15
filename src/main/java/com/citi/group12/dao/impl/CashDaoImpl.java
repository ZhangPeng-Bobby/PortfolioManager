package com.citi.group12.dao.impl;

import com.citi.group12.dao.CashDao;
import com.citi.group12.entity.Cash;
import com.citi.group12.entity.Investment;
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
public class CashDaoImpl implements CashDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Cash cash) {
        mongoTemplate.save(cash);
    }

    @Override
    public long update(Cash cash) {
        Query query = new Query(Criteria.where("id").is(cash.getId()));

        Update update = new Update();
        update.set("name", cash.getName()).set("type", cash.getType())
                .set("balance", cash.getBalance());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Investment.class);

        return result.getMatchedCount();
    }

    @Override
    public List<Cash> findAll() {
        return mongoTemplate.findAll(Cash.class);
    }

    @Override
    public Cash findOne(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Cash.class);
    }

    @Override
    public List<Cash> findByDateInterval(Date startDate, Date endDate) {
        Criteria c1 = Criteria.where("date").gte(startDate);
        Criteria c2 = Criteria.where("date").lte(endDate);
        return mongoTemplate.find(new Query(new Criteria().andOperator(c1, c2)), Cash.class);
    }

    @Override
    public void delete(String id) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Cash.class);
    }
}
