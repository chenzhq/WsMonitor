package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.bean.HostDO;
import com.ws.stoner.BootApplication;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.model.brief.HostBrief;
import com.ws.stoner.model.dto.BriefHostDTO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/** 
* HostServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 8, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class HostManagerImplTest {

    @Autowired
    private HostManager hostManager;

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
* Method: listHost() 
* 
*/ 
@Test
public void testListHost() throws Exception { 
//TODO: Test goes here...
    HostGetRequest hostGetRequest = new HostGetRequest();
    List<BriefHostDTO> hosts = hostManager.listHost(hostGetRequest);
    System.out.println(hosts.toString());
} 

/** 
* 
* Method: listDisableHost() 
* 
*/ 





/** 
*


/** 
* 
* Method: countAllHost() 
* 
*/ 
@Test
public void testCountAllHost() throws Exception { 
//TODO: Test goes here...

} 


} 
