package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.stoner.BootApplication;
import com.ws.stoner.daonew.TreeDAO;
import com.ws.stoner.model.mongo.hosttree.HostNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/** 
* HostTreeDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>12/25/2017</pre> 
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class TreeDAOImplTest {

    @Autowired
    private ZApi zApi;

    @Autowired
    private TreeDAO treeDAO;

@Before
public void before() throws Exception { 
    
    zApi.cacheLogin("5fce1b3e34b520afeffb37ce08c7cd66");

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: listHostTreeFromMongo() 
* 
*/ 
@Test
public void testListHostTreeFromMongo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getHostNodeByNodeId(String nodeId) 
* 
*/ 
@Test
public void testGetHostNodeByNodeId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insertHostNode(HostNode hostNode) 
* 
*/ 
@Test
public void testInsertHostNode() throws Exception { 
//TODO: Test goes here...
    HostNode hostNode = new HostNode(
            "g0",
            "root",
            "#"
    );
    treeDAO.insertHostNode(hostNode);

} 

/** 
* 
* Method: saveAll(List<HostNode> allHostNodes) 
* 
*/ 
@Test
public void testSaveAll() throws Exception { 
//TODO: Test goes here...
    List<HostNode> hostNodes = new ArrayList<>();
    HostNode hostNode1 = new HostNode(
            "g0",
            "root",
            "#"
    );
    HostNode hostNode2 = new HostNode(
            "g1",
            "sdf",
            "root"
    );
    HostNode hostNode3 = new HostNode(
            "g2",
            "ddsdd",
            "root"
    );
    hostNodes.add(hostNode1);
    hostNodes.add(hostNode2);
    hostNodes.add(hostNode3);
    treeDAO.saveAllHostNodes(hostNodes);

} 

/** 
* 
* Method: deleteAll() 
* 
*/ 
@Test
public void testDeleteAll() throws Exception { 
//TODO: Test goes here...
    treeDAO.deleteAllHostNodes();

}




} 
