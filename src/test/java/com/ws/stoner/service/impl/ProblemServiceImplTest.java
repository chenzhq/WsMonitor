package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.trigger.Trigger;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.service.ProblemService;
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
* ProblemServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 25, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ProblemServiceImplTest {

    @Autowired
    private ZApi zApi;

    @Autowired
    private ProblemService problemService;

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
* Method: listProblem(ProblemGetRequest request) 
* 
*/ 
@Test
public void testListProblem() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getRecentProblem(List<String> triggerIds) 
* 
*/ 
@Test
public void testGetRecentProblem() throws Exception { 
//TODO: Test goes here...
    List<BriefTriggerDTO> triggerDTOS = triggerService.listTriggersSkipDependent();
    List<String> triggerIds = new ArrayList<>();
    for(BriefTriggerDTO triggerDTO : triggerDTOS) {
        triggerIds.add(triggerDTO.getTriggerId());
    }
    System.out.println(problemService.getRecentProblem(triggerIds));
} 

/** 
* 
* Method: getLastProblems() 
* 
*/ 
@Test
public void testGetLastProblems() throws Exception { 
//TODO: Test goes here... 
} 


} 
