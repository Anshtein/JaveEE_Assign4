/********************************************************************egg***m******a**************n************
 * File: ProductTestSuite.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.invoke.MethodHandles;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.algonquincollege.cst8277.ejb.BuildUser;
import com.algonquincollege.cst8277.ejb.SimpleBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTestSuite {
    public static final String SHOPPING_CART_PU_NAME = "shopping_cart_jee5";
    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);

    public static EntityManagerFactory emf;
    public static EntityManager em;
    public static SimpleBean sb;
    protected BuildUser buildUser;

    private static Product prod1;
    private static Product prod2;
    private static Category cat1;
    private static Category cat2;

    /**
     * set up
     */
    @BeforeClass
    public static void oneTimeSetUp() {
        emf = Persistence.createEntityManagerFactory("shopping_cart_jee56");
        sb = new SimpleBean();
    }

    /**
     * set up before each method
     */
    @Before
    public void cleanUpCategoryAndProductTables() {
        logger.info("cleanUpCategoryAndProductTables::enter");
        em = emf.createEntityManager();
        //this.cleanUpProductTable();
        //this.cleanUpCategoryTable();
        // this.deleteAllEmpProjectRelationships();

        cat1 = new Category();
        cat1.setName("Category1");
        cat2 = new Category();
        cat2.setName("Category2");

        prod1 = new Product();
        prod1.setName("Product1");
        prod1.setPrice(0.12);
        prod2 = new Product();
        prod2.setName("Product2");
        prod2.setPrice(2.12);

        logger.info("cleanUpCategoryAndProductTables::exit");
    }


    @After
    public void closeEntityManager() {
        em.close();
    }

    /**
     * test create product
     * @throws NamingException
     */
    @Test
    public void createProduct() throws NamingException {
        logger.info("createProduct::enter");

        assertNotNull(prod1);

        em.getTransaction().begin();
        em.persist(prod1);
        em.getTransaction().commit();

        Product prod = em.find(Product.class, prod1.getId());

        assertNotNull(prod);

        logger.info("createProduct::exit");
    }

    /**
     * test read product
     */
    @Test
    public void readProduct() {
        logger.info("deleteProduct::enter");
        Product product = em.find(Product.class, 1);

        if(product != null && product.getId() > 0) {
            assertNotNull(product);
        }
        else
        {
            assertNull(product);
        }
    }

    /**
     * test delete product
     */
    @Test
    public void deleteProduct() {
        logger.info("deleteProduct::enter");
        Product product = em.find(Product.class, 2);

        if(product != null && product.getId() > 0) {
            em.getTransaction().begin();
            em.createNativeQuery("DELETE FROM CHOICE " + 
                    "WHERE PRODUCT_ID = " + product.getId()).executeUpdate();

            em.createNativeQuery("DELETE FROM CATEGORY_PROD " + 
                    "WHERE PROD_ID = " + product.getId()).executeUpdate();
            em.remove(product);
            em.getTransaction().commit();
            /*            
            em.createNativeQuery("DELETE FROM PRODUCT " + 
                    "WHERE ID = " + product.getId()).executeUpdate();*/

        }
        logger.info("deleteProduct::exit");
    }

    /**
     * delete all categores
     * @return int deleteCount
     */
    public int deleteAllCategories() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM CATEGORY").executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }

    public int deleteAllProdCatRelationships() {
        /*
         * em.getTransaction().begin(); int deletedCount =
         * em.createQuery("DELETE FROM CATEGORY_PROD").executeUpdate();
         * em.getTransaction().commit(); return deletedCount;
         */
        return 0;
    }

    /**
     * delete all products
     * @return int deleteCount
     */
    public int deleteAllProducts() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM PRODUCT").executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }

    /*public void cleanUpCategoryTable() {
        logger.info("cleanUpCategoryTable::enter");
        // make sure that category table is empty
        this.deleteAllCategories();
        List<Category> allCats = this.getAllCategories();
        assertTrue(allCats.size() == 0);
        logger.info("cleanUpCategoryTable::exit");
    }

    public void cleanUpProductTable() {
        logger.info("cleanUpProductTable::enter");
        // make sure that product table is empty
        this.deleteAllProducts();
        List<Product> allProds = this.getAllProducts();
        assertTrue(allProds.size() == 0);
        logger.info("cleanUpProductTable::exit");
    }*/

    /**
     * test get all categories
     */
    @Test
    public void getAllCategories() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> rootEntry = cq.from(Category.class);
        CriteriaQuery<Category> all = cq.select(rootEntry);
        TypedQuery<Category> allQuery = em.createQuery(all);

        assertTrue(allQuery.getResultList().size() > 0);
    }

    /**
     * test update product
     */
    @Test
    public void updateProduct() {
        logger.info("updateProduct::enter");
        em.getTransaction().begin();
        prod1.setName("NewNameProduct");
        em.merge(prod1);
        em.getTransaction().commit();
        logger.info("updateProduct::exit");
    }

    /**
     * test get all products
     */
    @Test
    public void getAllProducts() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> rootEntry = cq.from(Product.class);
        CriteriaQuery<Product> all = cq.select(rootEntry);
        TypedQuery<Product> allQuery = em.createQuery(all);

        assertTrue(allQuery.getResultList().size() > 0);
    }
}
