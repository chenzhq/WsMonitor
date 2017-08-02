package com.ws.stoner.utils;

import com.ws.stoner.BootApplication;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* StatusConverter Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 19, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class StatusConverterTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: StatusTransform(int customStatus) 
* 
*/ 
@Test
public void testStatusTransformCustomStatus() throws Exception { 
//TODO: Test goes here...
    System.out.println(StatusConverter.StatusTransform(0));
} 

/** 
* 
* Method: StatusTransform(int customStatus, int customAvailableState) 
* 
*/ 
@Test
public void testStatusTransformForCustomStatusCustomAvailableState() throws Exception { 
//TODO: Test goes here... 
}

    @Test
    public void testgetStatusByThresholdValue() throws Exception {
        System.out.println(StatusConverter.getStatusByThresholdValue(Float.parseFloat("100"),Float.parseFloat("20"),null,">"));
    }


} 
