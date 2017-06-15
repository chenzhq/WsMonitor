package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.model.brief.ApplicationBrief;
import com.ws.stoner.model.brief.HostBrief;
import com.ws.stoner.model.brief.HostGroupBrief;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.service.CountStateService;
import com.ws.stoner.service.FetchBriefService;
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
    private FetchBriefService fetchBriefService;

    @Autowired
    private CountStateService countStateService;

    @Autowired
    private CountStateServiceImpl countStateServiceImpl;

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
public void testHostState() throws Exception {
//TODO: Test goes here...
    StateNumDTO hosts = countStateService.CountHostState();
    System.out.println(hosts);

}


@Test
public void testListAllHost() throws Exception {
//TODO: Test goes here...
    List<HostBrief> hosts = fetchBriefService.listHost();
    System.out.println(hosts);

}

    @Test
    public void testListProblemHost() throws Exception {
//TODO: Test goes here...
        List<HostBrief> hosts = fetchBriefService.listProblemHost();
        System.out.println(hosts);

    }

@Test
public void testListOkHost() throws Exception {
//TODO: Test goes here...
    List<HostBrief> hosts = fetchBriefService.listOkHost();
    System.out.println(hosts);

}

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
    public void testHostGroupState() throws Exception {
//TODO: Test goes here...
        StateNumDTO hostGroup = countStateService.CountHostGroupState();
        System.out.println(hostGroup);

    }

    @Test
    public void testListAllHostGroup() throws Exception {
        List<HostGroupBrief> allHostGroups = fetchBriefService.listHostGroup();
        System.out.println(allHostGroups);
    }
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
public void testListAllApp() throws Exception {
    List<ApplicationBrief> allApp = fetchBriefService.listApp();
    System.out.println(allApp);
}



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

}

} 
