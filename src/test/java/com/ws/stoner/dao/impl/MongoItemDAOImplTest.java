package com.ws.stoner.dao.impl;

import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.MongoGraphDAO;
import com.ws.stoner.dao.MongoItemDAO;
import com.ws.stoner.model.DO.mongo.Item;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* MongoItemDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 19, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class MongoItemDAOImplTest {

    @Autowired
    private MongoItemDAO mongoItemDAO;
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: findItemByHostId(String hostId) 
* 
*/ 
@Test
public void testFindItemByHostId() throws Exception { 
//TODO: Test goes here...

    System.out.println(mongoItemDAO.getItemByItemId("43154"));
} 

/** 
* 
* Method: save(Item item) 
* 
*/ 
@Test
public void testSave() throws Exception { 
//TODO: Test goes here...
    Item item = new Item();
    item.setGraphName("CPU使用情况");
    item.setGraphType("line");
    item.setHostId("10084");
    item.setItemId("34496");
    mongoItemDAO.save(item);
}

    @Test
    public void testDelete() throws Exception {
        //TODO: Test goes here...

        mongoItemDAO.delete("43154");
    }




} 
