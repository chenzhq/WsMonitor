package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.stoner.BootApplication;
import com.ws.stoner.service.HostSerivce;
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
public class HostSerivceImplTest {

    @Autowired
    private HostSerivce hostSerivce;

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
    List<BriefHostDTO> hosts = hostSerivce.listHost(hostGetRequest);
    System.out.println(hosts.toString());
}

@Test
public void testListAllHost() throws Exception {
//TODO: Test goes here...
    List<BriefHostDTO> hosts = hostSerivce.listAllHost();
    System.out.println(hosts);

}

@Test
public void testListProblemHost() throws Exception {
//TODO: Test goes here...
    System.out.println(hostSerivce.listWarningHost());
    System.out.println(hostSerivce.countHightHost());

}

@Test
public void testListOkHost() throws Exception {
//TODO: Test goes here...
    List<BriefHostDTO> hosts = hostSerivce.listOkHost();
    System.out.println(hosts);

}

@Test
public void testCountAllHost() throws Exception {
//TODO: Test goes here...
    int allhostNum = hostSerivce.countAllHost();
    System.out.println(allhostNum);

}

@Test
public void testCountProblemHost() throws Exception {
    System.out.println(hostSerivce.countWarningHost());
    System.out.println(hostSerivce.countHightHost());
}

@Test
public void testCountOKHost() throws Exception {
    int okhostNum = hostSerivce.countOkHost();
    System.out.println(okhostNum);
}


} 
