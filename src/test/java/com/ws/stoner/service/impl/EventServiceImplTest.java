package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.service.EventService;
import com.ws.stoner.service.TriggerService;
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
* EventServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 25, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class EventServiceImplTest {

    @Autowired
    private ZApi zApi;

    @Autowired
    private EventService eventService;

    @Autowired
    private TriggerService triggerService;

@Before
public void before() throws Exception {
    zApi.cacheLogin("2d9cab00c19070e3fae36b8b83387e6a");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: listEvent(EventGetRequest request) 
* 
*/ 
@Test
public void testListEvent() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getEventByEventId(List<String> eventIds) 
* 
*/ 
@Test
public void testGetEventByEventId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAllEventsByTime(String beginTime, String endTime, List<String> triggerIds) 
* 
*/ 
@Test
public void testGetAllEventsByTime() throws Exception { 
//TODO: Test goes here...
    List<BriefTriggerDTO> triggerDTOS = triggerService.listTriggersSkipDependent();
    List<String> triggerIds = new ArrayList<>();
    for(BriefTriggerDTO triggerDTO : triggerDTOS) {
        triggerIds.add(triggerDTO.getTriggerId());
    }
    System.out.println(eventService.getAllEventsByTime("1503556552","1503642952",triggerIds));
} 

/** 
* 
* Method: getProblemEventsByTime(String beginTime, String endTime, List<String> triggerIds) 
* 
*/ 
@Test
public void testGetProblemEventsByTime() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getHistoryProblemsByTime(String beginTime, String end_time) 
* 
*/ 
@Test
public void testGetHistoryProblemsByTime() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAcknowledgeVOSByEventId(String eventId) 
* 
*/ 
@Test
public void testGetAcknowledgeVOSByEventId() throws Exception { 
//TODO: Test goes here... 
} 


} 
