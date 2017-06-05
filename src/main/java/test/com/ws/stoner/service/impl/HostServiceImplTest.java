package test.com.ws.stoner.service.impl; 

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.service.HostService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* HostServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 2, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class HostServiceImplTest { 

    @Autowired
    private HostService hostService;

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
    int maintenanceHostNum = hostService.countMaintenanceHost();
    System.out.println(maintenanceHostNum);
} 

/** 
* 
* Method: countDangerHost() 
* 
*/ 
@Test
public void testCountDangerHost() throws Exception { 
//TODO: Test goes here...
    int dangerHost = hostService.countDangerHost();
    System.out.println(dangerHost);

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
    int okHost = hostService.countOkHost();
    System.out.println(okHost);

} 

/** 
* 
* Method: countAllHostState() 
* 
*/ 
@Test
public void testCountAllHostState() throws Exception { 
//TODO: Test goes here...
    StateNumDTO stateNumDTO = hostService.countAllHostState();
    System.out.println(stateNumDTO.toString());
} 

/** 
* 
* Method: countAllHost() 
* 
*/ 
@Test
public void testCountAllHost() throws Exception { 
//TODO: Test goes here...
    int allHost = hostService.countAllHost();
    System.out.println(allHost);
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
