package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.bean.ApplicationDO;
import com.ws.stoner.BootApplication;
import com.ws.stoner.manager.ApplicationManager;
import com.ws.stoner.model.brief.ApplicationBrief;
import com.ws.stoner.model.dto.BriefPointDTO;
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
* ApplicationManagerImpl Tester.
* 
* @author <Authors name> 
* @since <pre>六月 8, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ApplicationManagerImplTest {

    @Autowired
    private ApplicationManager applicationManager;

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
* Method: listApplication() 
* 
*/ 
@Test
public void testListApplication() throws Exception { 
//TODO: Test goes here...
    ApplicationGetRequest apprequest = new ApplicationGetRequest();
    List<BriefPointDTO> listApp = applicationManager.listApplication(apprequest);
    System.out.println(listApp.toString());

}



} 
