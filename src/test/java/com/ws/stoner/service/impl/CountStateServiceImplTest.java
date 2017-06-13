package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.service.CountStateService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* CountStateServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 12, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class CountStateServiceImplTest {

    @Autowired
    private ZApi zApi;

    @Autowired
    private CountStateService countStateService;

@Before
public void before() throws Exception {
    zApi.cacheLogin("f558f7da83dea947f7c2d0def347b4f0");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: countAllHost() 
* 
*/ 
@Test
public void testCountAllHost() throws Exception { 
//TODO: Test goes here...
    int allhostNum = countStateService.countAllHost();
    System.out.println(allhostNum);

}

@Test
public void testCountProblemHost() throws Exception {
    int phostNum = countStateService.countProblemHost();
    System.out.println(phostNum);
}




} 
