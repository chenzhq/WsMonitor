package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.view.HostDetailPointVO;
import com.ws.stoner.service.PointSerivce;
import com.ws.stoner.model.dto.BriefPointDTO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/** 
* PointSerivceImpl Tester.
* 
* @author <Authors name> 
* @since <pre>七月 6, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class PointSerivceImplTest {

    @Autowired
    private PointSerivce pointSerivce;

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
* Method: countPoint(ApplicationGetRequest request) 
* 
*/ 
@Test
public void testCountPoint() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: listPoint(ApplicationGetRequest request) 
* 
*/ 
@Test
public void testListPoint() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countAllPoint() 
* 
*/ 
@Test
public void testCountAllPoint() throws Exception { 
//TODO: Test goes here...
    int allAppNum = pointSerivce.countAllPoint();
    System.out.println(allAppNum);
} 

/** 
* 
* Method: countProblemPoint() 
* 
*/ 
@Test
public void testCountProblemPoint() throws Exception { 
//TODO: Test goes here...
    System.out.println(pointSerivce.countWarningPoint());
    System.out.println(pointSerivce.countHighPoint());
} 

/** 
* 
* Method: countOkPoint() 
* 
*/ 
@Test
public void testCountOkPoint() throws Exception { 
//TODO: Test goes here...
    int okmAppNum = pointSerivce.countOkPoint();
    System.out.println(okmAppNum);
} 

/** 
* 
* Method: countAllPointByHostIds(List<String> hostIds) 
* 
*/ 
@Test
public void testCountAllPointByHostIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countProblemPointByHostIds(List<String> hostIds) 
* 
*/ 
@Test
public void testCountProblemPointByHostIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: listAllPoint() 
* 
*/ 
@Test
public void testListAllPoint() throws Exception { 
//TODO: Test goes here...
    List<BriefPointDTO> points = pointSerivce.listAllPoint();
    System.out.println(points);
} 

/** 
* 
* Method: listProblemPoint() 
* 
*/ 
@Test
public void testListProblemPoint() throws Exception { 
//TODO: Test goes here...
    System.out.println(pointSerivce.listWarningPoint());
    System.out.println(pointSerivce.listHighPoint());

}

    @Test
    public void testgetDetailPointByPointId() throws  Exception {
        System.out.println(pointSerivce.getDetailPointByPointId("1087"));
    }


} 
