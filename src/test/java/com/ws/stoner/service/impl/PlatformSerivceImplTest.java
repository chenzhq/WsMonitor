package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.service.PlatformSerivce;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/** 
* PlatformSerivceImpl Tester.
* 
* @author <Authors name> 
* @since <pre>七月 6, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class PlatformSerivceImplTest {
    @Autowired
    private PlatformSerivce platformSerivce;

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
* Method: listPlatform(HostGroupGetRequest request) 
* 
*/ 
@Test
public void testListPlatform() throws Exception { 
//TODO: Test goes here...

} 

/** 
* 
* Method: countPlatform(HostGroupGetRequest request) 
* 
*/ 
@Test
public void testCountPlatform() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countAllHostByPlatformIds(List<String> platformIds) 
* 
*/ 
@Test
public void testCountAllHostByPlatformIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countProblemHostByPlatformIds(List<String> platformIds) 
* 
*/ 
@Test
public void testCountProblemHostByPlatformIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countAllPlatform() 
* 
*/ 
@Test
public void testCountAllPlatform() throws Exception { 
//TODO: Test goes here...
    int allHostGroupNum = platformSerivce.countAllPlatform();
    System.out.println(allHostGroupNum);
} 

/** 
* 
* Method: countProblemPlatform() 
* 
*/ 
@Test
public void testCountProblemPlatform() throws Exception { 
//TODO: Test goes here...
    System.out.println(platformSerivce.countWarningPlatform());
    System.out.println(platformSerivce.countHightPlatform());
} 

/** 
* 
* Method: countOkPlatform() 
* 
*/ 
@Test
public void testCountOkPlatform() throws Exception { 
//TODO: Test goes here...
    int okHostGroupNum = platformSerivce.countOkPlatform();
    System.out.println(okHostGroupNum);

} 

/** 
* 
* Method: listAllPlatform() 
* 
*/ 
@Test
public void testListAllPlatform() throws Exception { 
//TODO: Test goes here...
    List<BriefPlatformDTO> allHostGroups = platformSerivce.listAllPlatform();
    System.out.println(allHostGroups);
} 

/** 
* 
* Method: listProblemPlatform() 
* 
*/ 
@Test
public void testListProblemPlatform() throws Exception { 
//TODO: Test goes here...
    System.out.println(platformSerivce.listWarningPlatform());
    System.out.println(platformSerivce.listHightPlatform());
} 


} 
