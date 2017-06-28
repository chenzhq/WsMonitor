package com.ws.stoner.service.impl;

import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.MongoGroupRepository;
import com.ws.stoner.model.mongoDO.MongoGroupDO;
import com.ws.stoner.service.MongoGroupService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    private MongoGroupService mongoGroupService;

    @Autowired
    private MongoGroupRepository mongoGroupRepository;

@Before
public void before() throws Exception { 
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
    List<MongoGroupDO> mongoGroupDOS = mongoGroupService.findAll();
    System.out.println(mongoGroupDOS);

}

@Test
    public void testSave() throws Exception {
    MongoGroupDO groupDO1 = new MongoGroupDO(
            "本机容器",
            "0",
            new String[]{"核心交换机","应用组"},
            new String[]{"10081","10086","10087"}
    );
    MongoGroupDO groupDO2 = new MongoGroupDO(
            "核心交换机",
            "1",
            new String[]{},
            new String[]{"10012","10011","10013"}
    );
    MongoGroupDO groupDO3 = new MongoGroupDO(
            "应用组",
            "1",
            new String[]{},
            new String[]{"10031","10032","10033"}
    );

    System.out.println(mongoGroupRepository.save(groupDO1));
    System.out.println(mongoGroupRepository.save(groupDO2));
    System.out.println(mongoGroupRepository.save(groupDO3));
}


} 
