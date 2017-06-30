package com.ws.stoner.dao;

import com.ws.stoner.model.DO.DOMongo.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by pc on 2017/6/28.
 */
@Component
public interface OverviewGroupRepository extends MongoRepository<Group,Long> {

    Group findByName(String name) ;


}
