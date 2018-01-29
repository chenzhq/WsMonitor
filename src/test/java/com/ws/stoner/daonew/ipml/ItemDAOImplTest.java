package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.daonew.ItemDAO;
import com.ws.stoner.model.mongo.hosttree.HostNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* ItemDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/18/2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ItemDAOImplTest {

    @Autowired
    private ZApi zApi;

    @Autowired
    private ItemDAO itemDAO;



@Before
public void before() throws Exception {
    zApi.cacheLogin("5fce1b3e34b520afeffb37ce08c7cd66");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getCoreItemByPlatIds(List<String> platformIds) 
* 
*/ 
@Test
public void testGetCoreItemByPlatIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getItemDTOByHostIds(List<String> hostIds) 
* 
*/ 
@Test
public void testGetItemDTOByHostIds() throws Exception { 
//TODO: Test goes here...

    System.out.println("qqq");
} 

/** 
* 
* Method: getCoreItemByHostIds(List<String> hostIds) 
* 
*/ 
@Test
public void testGetCoreItemByHostIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getItemDTOSByPointIds(List<String> pointIds) 
* 
*/ 
@Test
public void testGetItemDTOSByPointIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: listHostTreeFromMongo() 
* 
*/ 
@Test
public void testListHostTreeFromMongo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getHostNodeByNodeId(String nodeId) 
* 
*/ 
@Test
public void testGetHostNodeByNodeId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insertHostNode(HostNode hostNode) 
* 
*/ 
@Test
public void testInsertHostNode() throws Exception { 
//TODO: Test goes here...
    HostNode hostNode = new HostNode(
            "g0",
            "root",
            "#"
    );


} 


/** 
* 
* Method: listItem(ItemGetRequest request) 
* 
*/ 
@Test
public void testListItem() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = ItemDAOImpl.getClass().getMethod("listItem", ItemGetRequest.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
