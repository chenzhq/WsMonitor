package com.ws.stoner.dao.impl;

import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.MongoItemDAO;
import com.ws.stoner.dao.MongoPlatformTreeDAO;
import com.ws.stoner.model.DO.mongo.PlatformTree;
import com.ws.stoner.utils.RestResultGenerator;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_UPDATE_SUCCESS;

/** 
* MongoPlatformTreeDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 10, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class MongoPlatformTreeDAOImplTest {

    @Autowired
    private MongoPlatformTreeDAO mongoPlatformTreeDAO;

@Before
public void before() throws Exception {

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: save(PlatformTree platformTree) 
* 
*/ 
@Test
public void testSave() throws Exception { 
//TODO: Test goes here...
    PlatformTree child1 = new PlatformTree("01","child1","服务器");
    PlatformTree child2 = new PlatformTree("02","child2","数据库");
    PlatformTree child3 = new PlatformTree("03","child3","网络设备");
    PlatformTree child4 = new PlatformTree("04","child4","存储");
    List<PlatformTree> list1 = new ArrayList<>();
    List<PlatformTree> list2 = new ArrayList<>();
    List<PlatformTree> list3 = new ArrayList<>();
    list1.add(child1);
    list1.add(child2);
    list2.add(child3);
    list2.add(child4);
    PlatformTree parent1 = new PlatformTree("1","parent1","集群",list1);
    PlatformTree parent2 = new PlatformTree("2","parent2","集群",list2);
    list3.add(parent1);
    list3.add(parent2);
    PlatformTree root = new PlatformTree("0","root","业务平台",list3);
    mongoPlatformTreeDAO.save(root);
    System.out.println(RestResultGenerator.genResult(root, REST_UPDATE_SUCCESS).toString());
} 


} 
