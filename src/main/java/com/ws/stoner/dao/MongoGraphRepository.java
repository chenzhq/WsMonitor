package com.ws.stoner.dao;

import com.ws.stoner.model.DO.mongo.GraphType;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by pc on 2017/7/18.
 */
public interface MongoGraphRepository extends MongoRepository<GraphType,Long> {
}
