package com.ws.stoner.dao.impl;

import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.MongoGroupDAO;
import com.ws.stoner.dao.OverviewGroupRepository;
import com.ws.stoner.model.DO.mongo.Group;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/** 
* MongoGroupDAOImpl Tester.
* 
* @author <Authors name> 
* @since <pre>六月 30, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class MongoGroupDAOImplTest {

    @Autowired
    private MongoGroupDAO mongoGroupDAO;

    @Autowired
    private OverviewGroupRepository overviewGroupRepository;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: findMaxGroupCId() 
* 
*/ 
@Test
public void testFindMaxGroupCId() throws Exception { 
//TODO: Test goes here...
    Group group = mongoGroupDAO.findMaxGroupCId();
    System.out.println(group);

}

@Test
    public void testFindGroupByCId() throws Exception {
        Group group = mongoGroupDAO.findGroupByCId("1");
        System.out.println(group);
}

@Test
    public void testDelete() throws Exception {
       Group delGroup = new Group(
               "5959ceed89f1f105f8fb0d7d","服务器","0","5","0",new String[]{},new String[]{}
       );
       overviewGroupRepository.delete(delGroup);
}

@Test
public void testUpdateName() throws Exception {
    Group group = new Group("");
}

    @Test
    public void testBathUpdate() throws  Exception {
        List<Group> groups = new ArrayList<>();
        Group OA = overviewGroupRepository.findByName("OA平台");
        Group MAC = overviewGroupRepository.findByName("MAC");
        OA.setHostChildren(new String[]{"10084"});
        MAC.setHostChildren(new String[]{"10114"});
        groups.add(OA);
        groups.add(MAC);
        mongoGroupDAO.bathUpdateGroups(groups);
    }


} 
