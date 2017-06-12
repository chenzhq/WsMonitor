package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.bean.HostDO;
import com.ws.stoner.BootApplication;
import com.ws.stoner.manager.HostManager;
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
    List<HostDO> hosts = hostManager.listHost();
    System.out.println(hosts.toString());
} 

/** 
* 
* Method: listDisableHost() 
* 
*/ 
@Test
public void testListDisableHost() throws Exception { 
//TODO: Test goes here...
    List<HostDO> disableHosts = hostManager.listDisableHost();
    System.out.println(disableHosts);
} 
@Test
public void testListMaintenanceHost() throws  Exception {
    List<HostDO> maintenanceHosts = hostManager.listMaintenanceHost();
    System.out.println(maintenanceHosts.toString());
}
    @Test
    public void testListDangerHost() throws  Exception {
        List<HostDO> dangerHosts = hostManager.listDangerHost();
        System.out.println(dangerHosts.toString());
    }



/** 
* 
* Method: countDisableHost() 
* 
*/ 
@Test
public void testCountDisableHost() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countMaintenanceHost() 
* 
*/ 
@Test
public void testCountMaintenanceHost() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countDangerHost() 
* 
*/ 
@Test
public void testCountDangerHost() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countUnsupportedHost() 
* 
*/ 
@Test
public void testCountUnsupportedHost() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countOkHost() 
* 
*/ 
@Test
public void testCountOkHost() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countAllHostState() 
* 
*/ 
@Test
public void testCountAllHostState() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countAllHost() 
* 
*/ 
@Test
public void testCountAllHost() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getHost(String... hostId) 
* 
*/ 
@Test
public void testGetHost() throws Exception { 
//TODO: Test goes here... 
} 


} 
