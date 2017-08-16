package com.ws.stoner.dao.impl;

import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.MongoPlatformGraphDAO;
import com.ws.stoner.model.DO.mongo.PlatformGraph;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* MongoPlatformGraphDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 16, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class MongoPlatformGraphDAOImplTest {

    @Autowired
    private MongoPlatformGraphDAO mongoPlatformGraphDAO;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: save(PlatformGraph platformGraph) 
* 
*/ 
@Test
public void testSave() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findGraphsByHostIds(List<String> hostIds) 
* 
*/ 
@Test
public void testFindGraphsByHostIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findGraphsByPlatformId(String platformId) 
* 
*/ 
@Test
public void testFindGraphsByPlatformId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findGraphByItemId(String itemId) 
* 
*/ 
@Test
public void testFindGraphByItemId() throws Exception { 
//TODO: Test goes here...
    System.out.println(mongoPlatformGraphDAO.findGraphByItemId("32884"));
} 

/** 
* 
* Method: update(PlatformGraph platformGraph) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here...
    PlatformGraph platformGraph = new PlatformGraph(
            "32884","","","CPU使用情况","area"
    );
    mongoPlatformGraphDAO.update(platformGraph);

} 

/** 
* 
* Method: delete(String itemId) 
* 
*/ 
@Test
public void testDelete() throws Exception { 
//TODO: Test goes here...
    mongoPlatformGraphDAO.delete("32884");
} 


} 
