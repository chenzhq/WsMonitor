package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.service.TriggerService;
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
* TriggerSerivceImpl Tester.
* 
* @author <Authors name> 
* @since <pre>六月 8, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class TriggerServiceImplImplTest {

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private ZApi zApi;

@Before
public void before() throws Exception {
    zApi.cacheLogin("2d9cab00c19070e3fae36b8b83387e6a");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: listTrigger() 
* 
*/ 
@Test
public void testListTrigger() throws Exception { 
//TODO: Test goes here...
    TriggerGetRequest triggerGetRequest = new TriggerGetRequest();
    List<BriefTriggerDTO> triggerDOList = triggerService.listTrigger(triggerGetRequest);
    System.out.println(triggerDOList.toString());
} 

@Test
    public void testListUnknownTrigger() throws Exception {
//    List<TriggerDO> unknownTriggers = triggerService.listUnknownTrigger();
//    System.out.println(unknownTriggers);
}

    @Test
    public void testGetTriggersByItemIds() throws Exception {
        List<String > itemIds = new ArrayList<>();
        itemIds.add("32884");
        List<BriefTriggerDTO> briefTriggerDTOS = triggerService.getTriggersByItemIds(itemIds);
        System.out.println(briefTriggerDTOS);
    }

    @Test
    public void testlistProblemListVO() throws Exception {
        System.out.println(triggerService.listProblemListVO());
    }

} 
