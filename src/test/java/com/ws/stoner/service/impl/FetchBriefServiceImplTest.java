package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.view.DashboardHostVO;
import com.ws.stoner.service.FetchBriefService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/** 
* FetchBriefServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 19, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class FetchBriefServiceImplTest {

    @Autowired
    private ZApi zApi;

    @Autowired
    private FetchBriefService fetchBriefService;


    @Before
public void before() throws Exception {

        zApi.cacheLogin("f558f7da83dea947f7c2d0def347b4f0");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: listDashBoardHosts() 
* 
*/ 
@Test
public void testListDashBoardHosts() throws Exception { 
//TODO: Test goes here...
    List<DashboardHostVO> hostVOS = fetchBriefService.listDashBoardHosts();
    System.out.println(hostVOS);
} 

/** 
* 
* Method: listHost() 
* 
*/ 
@Test
public void testListHost() throws Exception { 
//TODO: Test goes here...
    List<BriefHostDTO> hosts = fetchBriefService.listHost();
    System.out.println(hosts);
} 

/** 
* 
* Method: listProblemHost() 
* 
*/ 
@Test
public void testListProblemHost() throws Exception { 
//TODO: Test goes here...
    List<BriefHostDTO> hosts = fetchBriefService.listProblemHost();
    System.out.println(hosts);

} 

/** 
* 
* Method: listOkHost() 
* 
*/ 
@Test
public void testListOkHost() throws Exception {
//TODO: Test goes here... 
}

    /**
     *
     * Method: template
     *
     */
    @Test
    public void testListTemplate() throws Exception {
//TODO: Test goes here...
        List<BriefTemplateDTO> templates = fetchBriefService.listAllTemplate();
        System.out.println(templates);
    }

    /**
* 
* Method: listPlatform() 
* 
*/ 
@Test
public void testListPlatform() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: listApp() 
* 
*/ 
@Test
public void testListApp() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: listBriefProblems() 
* 
*/ 
@Test
public void testListBriefProblems() throws Exception { 
//TODO: Test goes here... 
} 


} 
