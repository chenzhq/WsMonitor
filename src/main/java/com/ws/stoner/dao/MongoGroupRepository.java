package com.ws.stoner.dao;

import com.ws.stoner.model.mongoDO.MongoGroupDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pc on 2017/6/28.
 */
@Repository
public interface MongoGroupRepository extends MongoRepository<MongoGroupDO,String> {

}
