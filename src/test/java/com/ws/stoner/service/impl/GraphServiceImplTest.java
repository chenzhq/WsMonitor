package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.view.HostDetailItemVO;
import com.ws.stoner.service.GraphService;
import com.ws.stoner.service.HostService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/** 
* GraphServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 26, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class GraphServiceImplTest {

    @Autowired
    private GraphService graphService;

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
* Method: getGraphTypeByValueTypeFromMongo(String valueType) 
* 
*/ 
@Test
public void testGetGraphTypeByValueTypeFromMongo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getGraphItemByHostId(String hostId) 
* 
*/ 
@Test
public void testGetGraphItemByHostId() throws Exception { 
//TODO: Test goes here...
    List<HostDetailItemVO> itemVOS = graphService.getGraphItemByHostId("10084");
    System.out.println(itemVOS);
} 

/** 
* 
* Method: getGraphItemByPointId(String pointId, int time) 
* 
*/ 
@Test
public void testGetGraphItemByPointId() throws Exception { 
//TODO: Test goes here... 
} 


} 
