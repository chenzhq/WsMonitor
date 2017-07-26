package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.bean.ItemDO;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.view.HostDetailPointItemVO;
import com.ws.stoner.service.ItemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/** 
* ItemSerivceImpl Tester.
* 
* @author <Authors name> 
* @since <pre>六月 9, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ItemServiceImplImplTest {

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
    ItemGetRequest itemGetRequest = new ItemGetRequest();
    List<BriefItemDTO> items = itemService.listItem(itemGetRequest);
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

    @Test
    public void testGetItemsWithTriggersByPointIds() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add("1087");
        List<BriefItemDTO> briefItemDTOS = itemService.getItemsWithTriggersByPointIds(ids);
        System.out.println(briefItemDTOS);
    }

    @Test
    public void testgetValueItemByPointIds() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add("1087");
        List<BriefItemDTO> itemDTOS = itemService.getValueItemsByPointIds(ids);
        System.out.println(itemDTOS);
    }

    @Test
    public void testgetItemDatasByItemId() throws Exception {
        List<HostDetailPointItemVO> itemVOS = itemService.getItemDatasByItemId("37189",40);
        System.out.println(itemVOS);
    }


} 
