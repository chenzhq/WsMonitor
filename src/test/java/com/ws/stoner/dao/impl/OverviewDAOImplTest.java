package com.ws.stoner.dao.impl;

import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.OverviewDAO;
import com.ws.stoner.dao.OverviewGroupRepository;
import com.ws.stoner.model.DO.mongo.Group;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* OverviewDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 30, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class OverviewDAOImplTest {

    @Autowired
    private OverviewDAO overviewDAO;

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
    Group group = overviewDAO.findMaxGroupCId();
    System.out.println(group);

}

@Test
    public void testFindGroupByCId() throws Exception {
        Group group =overviewDAO.findGroupByCId("1");
        System.out.println(group);
}

@Test
    public void testDelete() throws Exception {
       Group delGroup = new Group(
               "5959ceed89f1f105f8fb0d7d","服务器","0","5","0",new String[]{},new String[]{}
       );
       overviewGroupRepository.delete(delGroup);
}



} 
