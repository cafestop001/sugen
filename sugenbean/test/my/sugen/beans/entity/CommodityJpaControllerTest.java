/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.sugen.beans.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cafestop
 */
public class CommodityJpaControllerTest {

    private static EntityManagerFactory emf = null;
    private CommodityJpaController instance = null;

    public CommodityJpaControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("sugenbeanPU");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new CommodityJpaController(emf);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEntityManager method, of class CommodityJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class CommodityJpaController.
     */
    @Test
    @SuppressWarnings("empty-statement")
    public void testCreate() throws Exception {
        System.out.println("create");
        ArrayList<String> al = new ArrayList<String>();
        al.add("红色");
        al.add("宝蓝色");
        Commodity commodity = new Commodity();
        commodity.setColors(al);
        instance.create(commodity);
//        instance.destroy(commodity.getId());
    }

    /**
     * Test of edit method, of class CommodityJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Commodity commodity = new Commodity();
        instance.edit(commodity);
    }

    /**
     * Test of destroy method, of class CommodityJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        long id = 0L;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCommodityEntities method, of class CommodityJpaController.
     */
    @Test
    public void testFindCommodityEntities_0args() {
        System.out.println("findCommodityEntities");
        List<Commodity> expResult = null;
        List<Commodity> result = instance.findCommodityEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCommodityEntities method, of class CommodityJpaController.
     */
    @Test
    public void testFindCommodityEntities_int_int() {
        System.out.println("findCommodityEntities");
        int maxResults = 0;
        int firstResult = 0;
        List<Commodity> expResult = null;
        List<Commodity> result = instance.findCommodityEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCommodity method, of class CommodityJpaController.
     */
    @Test
    public void testFindCommodity() {
        System.out.println("findCommodity");
        long id = 0L;
        Commodity expResult = null;
        Commodity result = instance.findCommodity(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommodityCount method, of class CommodityJpaController.
     */
    @Test
    public void testGetCommodityCount() {
        System.out.println("getCommodityCount");
        int expResult = 0;
        int result = instance.getCommodityCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
