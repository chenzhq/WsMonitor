package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.MongoItemDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by pc on 2017/7/13.
 */
@Repository
public class MongoItemDAOImpl implements MongoItemDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据hostId，查询 items
     */
    @Override
    public List<Item> findItemByHostId(String hostId) throws DAOException {
        Query query=new Query(Criteria.where("hostId").is(hostId));
        List<Item> item = mongoTemplate.find(query, Item.class);
        return item;
    }

    /**
     * 根据 itemId ，查询 item
     * @param itemId
     * @return
     * @throws DAOException
     */
    @Override
    public Item getItemByItemId(String itemId) throws DAOException {
        Query query=new Query(Criteria.where("itemId").is(itemId));
        Item item = mongoTemplate.findOne(query, Item.class);
        return item;
    }

    @Override
    public void save(Item item) throws DAOException {

        mongoTemplate.insert(item);

    }

    /**
     * 更新保存 item
     */
    @Override
    public void update(Item item) throws DAOException {
        Query query = Query.query(Criteria.where("item_id").is(item.getItemId()));
        Update update = Update.update("item_id",item.getItemId()).set("host_id",item.getHostId()).set("graph_name",item.getGraphName()).set("graph_type",item.getGraphType());
        mongoTemplate.updateFirst(query, update, Item.class);

    }

    /**
     * 根据 itemId 删除指定 item
     * @param itemId
     * @throws DAOException
     */
    @Override
    public void delete(String itemId) throws DAOException {
        Query query=new Query(Criteria.where("itemId").is(itemId));
        mongoTemplate.remove(query,Item.class);
    }
}
