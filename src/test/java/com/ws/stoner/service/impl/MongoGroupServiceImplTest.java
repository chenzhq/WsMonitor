package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.MongoGroupRepository;
import com.ws.stoner.model.mongoDO.MongoGroupDO;
import com.ws.stoner.model.view.MongoGroupVO;
import com.ws.stoner.service.MongoGroupService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
* MongoGroupServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 28, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class MongoGroupServiceImplTest {

    @Autowired
    private ZApi zApi;

    @Autowired
    private MongoGroupService mongoGroupService;

    @Autowired
    private MongoGroupRepository mongoGroupRepository;


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
    //List<MongoGroupDO> mongoGroupDOS = mongoGroupRepository.findAll();
    MongoGroupDO mongoGroupDO = mongoGroupRepository.findByName("root");
    System.out.println(mongoGroupDO);

}

@Test
    public void testSave() throws Exception {
    MongoGroupDO groupDO1 = new MongoGroupDO(
            "root",
            "-1",
            "01",
            null,
            new String[]{"交换机","数据库","服务器"},
            new String[]{"10114","10167","10164"}
    );
    MongoGroupDO groupDO2 = new MongoGroupDO(
            "交换机",
            "0",
            "02",
            "2",
            new String[]{},
            new String[]{"10234"}
    );
    MongoGroupDO groupDO3 = new MongoGroupDO(
            "数据库",
            "0",
            "03",
            "3",
            new String[]{},
            new String[]{"10155","10150"}
    );

    MongoGroupDO groupDO4 = new MongoGroupDO(
            "服务器",
            "0",
            "04",
            "3",
            new String[]{},
            new String[]{"10084"}
    );


    System.out.println(mongoGroupRepository.save(groupDO1));
    System.out.println(mongoGroupRepository.save(groupDO2));
    System.out.println(mongoGroupRepository.save(groupDO3));
    System.out.println(mongoGroupRepository.save(groupDO4));
}


    @Test
    public void testListMongoGroup() throws Exception{
        List<MongoGroupVO> mongoGroupVOS = mongoGroupService.listMongoGroup();
        System.out.println(mongoGroupVOS);
    }

    @Test
    public void testListMongoTree() throws Exception{
        List<MongoGroupVO> mongoGroupVOS = mongoGroupService.getGroupTree("root",new ArrayList<MongoGroupVO>());
        Collections.reverse(mongoGroupVOS);
        System.out.println(mongoGroupVOS);
    }

    @Test
    public void testListMongo() throws Exception {
    List<MongoGroupVO> mongoGroupVOS = mongoGroupService.listMongo();
    System.out.println(mongoGroupVOS);
    }


} 
