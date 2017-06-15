package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.bean.ApplicationDO;
import com.ws.stoner.BootApplication;
import com.ws.stoner.manager.ApplicationManager;
import com.ws.stoner.model.brief.ApplicationBrief;
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
* ApplicationManagerImpl Tester.
* 
* @author <Authors name> 
* @since <pre>六月 8, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ApplicationManagerImplTest {

    @Autowired
    private ApplicationManager applicationManager;

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
* Method: listApplication() 
* 
*/ 
@Test
public void testListApplication() throws Exception { 
//TODO: Test goes here...
    ApplicationGetRequest apprequest = new ApplicationGetRequest();
    List<ApplicationBrief> listApp = applicationManager.listApplication(apprequest);
    System.out.println(listApp.toString());

}

@Test
    public void testListDisableApp() throws  Exception {
        int disableAppNum = applicationManager.countDisableApp();
        System.out.println(disableAppNum);
}

    @Test
    public void testListMaintencanceApp() throws  Exception {
        int maintencanceAppNum = applicationManager.countMaintenanceApp();
        System.out.println(maintencanceAppNum);
    }

       @Test
    public void testListAppByItemIds() throws  Exception {
        List<String> itemIds = new ArrayList<String>();
        itemIds.add("23167");
        itemIds.add("23174");
        itemIds.add("23181");
        List<ApplicationDO> apps = applicationManager.listAppByItemIds(itemIds);
        System.out.println(apps);
    }

    @Test
    public void testCountAppByItemIds() throws  Exception {
        List<String> itemIds = new ArrayList<String>();
        itemIds.add("23167");
        itemIds.add("23174");
        itemIds.add("23181");
        int apps = applicationManager.countAppByItemIds(itemIds);
        System.out.println(apps);
    }

    @Test
    public void testCountUnknownApp() throws Exception {
        int unknownApp = applicationManager.countUnknownApp();
        System.out.println(unknownApp);
    }

} 
