package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.dto.BriefValuemapDTO;
import com.ws.stoner.service.ValuemapService;
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
* ValuemapServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 2, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ValuemapServiceImplTest {

    @Autowired
    private ValuemapService valuemapService;
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
* Method: listValuemap(ValuemapGetRequest request) 
* 
*/ 
@Test
public void testListValuemap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getValuemapByIds(List<String> valuemapIds) 
* 
*/ 
@Test
public void testGetValuemapByIds() throws Exception { 
//TODO: Test goes here...
    List<String> ids = new ArrayList<>();
    ids.add("4");
    List<BriefValuemapDTO> valuemapDTOS = valuemapService.getValuemapByIds(ids);
    System.out.println(valuemapDTOS);

}

    @Test
    public void testgetNewValueById() throws Exception {
        System.out.println(valuemapService.getNewValueById("4","3"));
    }


} 
