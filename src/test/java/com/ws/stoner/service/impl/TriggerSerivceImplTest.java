package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.stoner.BootApplication;
import com.ws.stoner.service.TriggerSerivce;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class TriggerSerivceImplTest {

    @Autowired
    private TriggerSerivce triggerSerivce;

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
* Method: listTrigger() 
* 
*/ 
@Test
public void testListTrigger() throws Exception { 
//TODO: Test goes here...
    TriggerGetRequest triggerGetRequest = new TriggerGetRequest();
    List<BriefTriggerDTO> triggerDOList = triggerSerivce.listTrigger(triggerGetRequest);
    System.out.println(triggerDOList.toString());
} 

@Test
    public void testListUnknownTrigger() throws Exception {
//    List<TriggerDO> unknownTriggers = triggerSerivce.listUnknownTrigger();
//    System.out.println(unknownTriggers);
}

} 
