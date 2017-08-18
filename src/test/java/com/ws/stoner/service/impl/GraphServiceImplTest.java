package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.DO.mongo.PlatformTree;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.view.HostDetailItemVO;
import com.ws.stoner.service.GraphService;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.TemplateService;
import com.ws.stoner.utils.RestResultGenerator;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_UPDATE_SUCCESS;

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
    private TemplateService templateService;

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

    @Test
    public void testgetGraphItemByItemId() throws Exception {
        System.out.println(graphService.getGraphItemByItemId("43162"));
    }

    @Test
    public void testgetPlatTreeByPlatformId() throws  Exception {
        System.out.println(graphService.getPlatTreeByPlatformId("8"));
    }

    @Test
    public void testinitPlatTree() throws Exception {
        System.out.println(graphService.initPlatTree());
    }

    @Test
    public void testgetPlatformGraphByhostIds() throws Exception {
        List<String> hostIds = new ArrayList<>();
        hostIds.add("10205");
        System.out.println(graphService.getPlatformGraphsByhostIds(hostIds));
    }



} 
