package com.citi.group12.dao.impl;

import com.citi.group12.dao.InvestmentDao;
import com.citi.group12.entity.Investment;
import com.citi.group12.entity.PortType;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvestmentDaoImpl implements InvestmentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Investment investment) {
        mongoTemplate.save(investment);
    }

    @Override
    public long update(Investment investment) {
        //修改的条件
        Query query = new Query(Criteria.where("id").is(investment.getId()));

        //修改的内容
        Update update = new Update();
        update.set("symbol",investment.getSymbol()).set("name",investment.getName())
                .set("type",investment.getType()).set("purchasedDate",investment.getPurchasedDate())
                .set("share",investment.getShare()).set("purchasedPrice",investment.getPurchasedPrice())
                .set("exchange",investment.getExchange());

        //更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query,update,Investment.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        return result.getMatchedCount();

    }

    @Override
    public List<Investment> findAll() {
        return mongoTemplate.findAll(Investment.class);

    }

    @Override
    public Investment findOne(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Investment.class);
    }

    @Override
    public List<Investment> findByType(PortType type) {
        return mongoTemplate.find(new Query(Criteria.where("type").is(type)), Investment.class);
    }

    @Override
    public void delete(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Investment.class);

    }
}
