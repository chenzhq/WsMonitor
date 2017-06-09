package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.bean.ItemDO;
import com.ws.stoner.BootApplication;
import com.ws.stoner.service.ItemService;
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
* ItemServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 9, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ZApi zApi;

@Before
public void before() throws Exception {
    zApi.cacheLogin("f558f7da83dea947f7c2d0def347b4f0");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: listItem() 
* 
*/ 
@Test
public void testListItem() throws Exception { 
//TODO: Test goes here...
    List<ItemDO> items = itemService.listItem();
    System.out.println(items.toString());

}

    @Test
    public void testListItemByTriggerIds() throws Exception {
//TODO: Test goes here...
        List<String> triggerIds = new ArrayList<String>();
        triggerIds.add("10021");
        triggerIds.add("10047");
        List<ItemDO> items = itemService.listItemByTriggerIds(triggerIds);
        System.out.println(items.toString());

    }


} 
