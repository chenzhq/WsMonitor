package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.OverviewGroupRepository;
import com.ws.stoner.model.DO.DOMongo.Group;
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
            "01",
            null,
            new String[]{"交换机","数据库","服务器"},
            new String[]{"10114","10167","10164"}
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
    System.out.println(overviewGroupRepository.save(groupDO2));
    System.out.println(overviewGroupRepository.save(groupDO3));
    System.out.println(overviewGroupRepository.save(groupDO4));
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


} 
