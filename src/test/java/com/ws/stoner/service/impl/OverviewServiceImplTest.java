package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.OverviewGroupRepository;
import com.ws.stoner.model.DO.mongo.Group;
import com.ws.stoner.model.dto.OverviewCreateGroupDTO;
import com.ws.stoner.model.dto.OverviewDelGroupDTO;
import com.ws.stoner.model.dto.OverviewMoveGroupDTO;
import com.ws.stoner.model.view.OverviewVO;
import com.ws.stoner.service.OverviewService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/** 
* OverviewServiceImpl Tester.
* 
* @author <Authors name> 
* @since <pre>六月 28, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class OverviewServiceImplTest {

    @Autowired
    private ZApi zApi;

    @Autowired
    private OverviewService overviewService;

    @Autowired
    private OverviewGroupRepository overviewGroupRepository;


@Before
public void before() throws Exception {
    zApi.cacheLogin("f558f7da83dea947f7c2d0def347b4f0");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: findAll() 
* 
*/ 
@Test
public void testFindAll() throws Exception { 
//TODO: Test goes here...
    //List<Group> mongoGroupDOS = mongoGroupRepository.findAll();
    Group group = overviewGroupRepository.findByName("root");
    System.out.println(group);

}

@Test
    public void testSave() throws Exception {
    Group groupDO1 = new Group(
            "root",
            "-1",
            "0",
            "null",
            new String[]{},
            new String[]{}
    );
    Group groupDO2 = new Group(
            "交换机",
            "0",
            "02",
            "2",
            new String[]{},
            new String[]{"10234"}
    );
    Group groupDO3 = new Group(
            "数据库",
            "0",
            "03",
            "3",
            new String[]{},
            new String[]{"10155","10150"}
    );

    Group groupDO4 = new Group(
            "服务器",
            "0",
            "04",
            "3",
            new String[]{},
            new String[]{"10084"}
    );


    System.out.println(overviewGroupRepository.save(groupDO1));
//    System.out.println(overviewGroupRepository.save(groupDO2));
//    System.out.println(overviewGroupRepository.save(groupDO3));
//    System.out.println(overviewGroupRepository.save(groupDO4));
}


    @Test
    public void testListMongoTree() throws Exception{
//        List<OverviewVO> mongoGroupVOS = overviewService.getGroupTree("root",new ArrayList<OverviewVO>());
//        Collections.reverse(mongoGroupVOS);
//        System.out.println(mongoGroupVOS);
    }

    @Test
    public void testListMongo() throws Exception {
    List<OverviewVO> overviewVOS = overviewService.listOverviewGroup();
    System.out.println(overviewVOS);
    }

    @Test
    public  void testcreateOverviewGroup() throws Exception {

        OverviewCreateGroupDTO ocg1 = overviewService.createOverviewGroup("OA平台","g0");
        OverviewCreateGroupDTO ocg2 = overviewService.createOverviewGroup("MAC","g0");
        OverviewCreateGroupDTO ocg3 = overviewService.createOverviewGroup("PAS","g0");
        OverviewCreateGroupDTO ocg4 = overviewService.createOverviewGroup("HIS","g0");

        System.out.println(ocg1);
        System.out.println(ocg2);
        System.out.println(ocg3);
        System.out.println(ocg4);

          OverviewCreateGroupDTO ocg5 = overviewService.createOverviewGroup("服务器","g1");

    }

    @Test
    public void testDelOverviewGroup() throws Exception {
        OverviewDelGroupDTO odg = overviewService.deleteOverviewGroup("1");
        System.out.println(odg);
    }

    @Test
    public void testMoveOverviewGroup() throws Exception {
        OverviewMoveGroupDTO omg = overviewService.moveOverviewGroup("g5","g1","g2");
    }

    @Test
    public void testInit() throws Exception {
        testSave();
        testListMongo();
        testcreateOverviewGroup();

    }

} 
