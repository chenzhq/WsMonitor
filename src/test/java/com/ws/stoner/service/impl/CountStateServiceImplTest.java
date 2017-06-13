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

import java.util.List;

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

//host相关测试
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

    @Test
    public void testCountOKHost() throws Exception {
        int okhostNum = countStateService.countOkHost();
        System.out.println(okhostNum);
    }


//hostgroup相关测试
    @Test
    public void testCountAllHostGroup() throws Exception {
        int allHostGroupNum = countStateService.countAllHostGroup();
        System.out.println(allHostGroupNum);
    }

    @Test
    public void testCountProblemHostGroup() throws Exception {
        int problemHostGroupNum = countStateService.countProblemHostGroup();
        System.out.println(problemHostGroupNum);
    }


//app相关测试
@Test
public void testCountAllApp() throws Exception {
    int allAppNum = countStateService.countAllApp();
    System.out.println(allAppNum);
}

@Test
public void testCountProbelmApp() throws Exception {
    int problemAppNum = countStateService.countProblemApp();
    System.out.println(problemAppNum);
}


//trigger测试相关
@Test
public void testGetProblemTrigger() throws Exception {
    List<String> triggerIds = countStateService.getProblemTriggerIds();
    System.out.println(triggerIds);
}

} 
