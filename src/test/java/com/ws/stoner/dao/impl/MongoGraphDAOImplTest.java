package com.ws.stoner.dao.impl;

import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.MongoGraphDAO;
import com.ws.stoner.model.DO.mongo.GraphType;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* MongoGraphDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 18, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class MongoGraphDAOImplTest {


    @Autowired
    private MongoGraphDAO mongoGraphDAO;

    @Before
public void before() throws Exception {


} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: findGraphTypeByValueType(Integer valueType) 
* 
*/ 
@Test
public void testFindGraphTypeByValueType() throws Exception { 
//TODO: Test goes here...
    GraphType graphType = mongoGraphDAO.findGraphTypeByValueType("0");
    System.out.println(graphType);
} 


} 
