package com.ws.stoner.dao.impl;

import com.ws.stoner.BootApplication;
import com.ws.stoner.dao.MongoItemDAO;
import com.ws.stoner.dao.MongoPlatformTreeDAO;
import com.ws.stoner.model.DO.mongo.PlatformTree;
import com.ws.stoner.model.DO.mongo.PlatformTreeManager;
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
    PlatformTreeManager child1 = new PlatformTreeManager("10205","ZKF_WIN_90","设备");
    PlatformTreeManager child2 = new PlatformTreeManager("10206","win_yang_feng","设备");
    List<PlatformTreeManager> list1 = new ArrayList<>();
    List<PlatformTreeManager> list2 = new ArrayList<>();
    List<PlatformTreeManager> list3 = new ArrayList<>();
    list1.add(child1);
    list1.add(child2);
    PlatformTreeManager parent1 = new PlatformTreeManager("1","服务器host","集群",list1);
    PlatformTreeManager parent2 = new PlatformTreeManager("2","数据库data","集群",list2);
    list3.add(parent1);
    list3.add(parent2);
    PlatformTreeManager root = new PlatformTreeManager("8","windows","业务平台",list3);
    mongoPlatformTreeDAO.save(root);
    System.out.println(RestResultGenerator.genResult(root, REST_UPDATE_SUCCESS).toString());
} 

@Test
public void testdemo() throws Exception {
    PlatformTreeManager root = new PlatformTreeManager("111","ceshi","业务平台","zhengc");
    mongoPlatformTreeDAO.save(root);
}


} 
