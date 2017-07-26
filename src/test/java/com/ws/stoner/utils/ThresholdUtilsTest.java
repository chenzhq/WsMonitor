package com.ws.stoner.utils;

import com.ws.stoner.BootApplication;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* ThresholdUtils Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 25, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ThresholdUtilsTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getThresholdValue(String expression) 
* 
*/ 
@Test
public void testGetThresholdValue() throws Exception { 
//TODO: Test goes here...
    System.out.println(ThresholdUtils.getThresholdValue("{16252}>1000"));
} 

    @Test
    public void testGetgetTransformValue() throws Exception {
        System.out.println(ThresholdUtils.getTransformValue(" 60m"));
    }

    @Test
    public void testtransformValueUnits() throws Exception {
        System.out.println(ThresholdUtils.transformValueUnits("32587","s"));
    }
} 
