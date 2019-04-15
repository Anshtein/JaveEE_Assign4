package com.algonquincollege.cst8277.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.algonquincollege.cst8277.ejb.BuildUser;
import com.algonquincollege.cst8277.ejb.SimpleBean;

public class ProductCategoryTestSuite {
    public static final String SHOPPING_CART_PU_NAME = "shopping_cart_jee5";
    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);
    
    public static EntityManagerFactory emf;
    public static EntityManager em;
    public static SimpleBean sb;
    protected BuildUser buildUser;
    
    private static Category cat1;
    private static Category cat2;
    private static Product prod1;
    private static Product prod2;

    
    @BeforeClass
    public static void oneTimeSetUp() {
        emf = Persistence.createEntityManagerFactory("shopping_cart_jee56");
        sb = new SimpleBean();
    }

    @Before
    public void cleanUpEmployeeAndProductTables() {
        logger.info("cleanUpEmployeeAndProductTables::enter");
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
        
        logger.info("cleanUpEmployeeAndProductTables::exit");
    }

    @After
    public void closeEntityManager() {
        em.close();
    }

    @Test
    public void createCategory() {
        logger.info("createCategory::enter");
        em.getTransaction().begin();
        em.persist(cat1);
        em.getTransaction().commit();
        logger.info("createCategory::exit");
    }
    
    @Test
    public void updateCategory() {
        logger.info("updateCategory::enter");
        Category cat = em.find(Category.class, 1);
        
        if(cat != null && cat.getId() > 0) {
            em.getTransaction().begin();
            cat.setName("NewCategoryUpdate");
            em.merge(cat);
            em.getTransaction().commit();
            
            cat = em.find(Category.class, 1);
            
            assertTrue(cat.getName().equals("NewCategoryUpdate"));
        }
        logger.info("updateCategory::exit");
    }
    
    @Test
    public void deleteCategory() {
        logger.info("deleteCategory::enter");
        
        int id = cat1.getId();
        
        em.getTransaction().begin();
        em.remove(cat1);
        em.getTransaction().commit();
        
        Category cat = em.find(Category.class, id);
        
        assertNull(cat);
        
        logger.info("deleteCategory::exit");
    }
    
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

    public int deleteAllProducts() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM PRODUCT").executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }

    public void cleanUpCategoryTable() {
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
    }
    
    public List<Category> getAllCategories() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> rootEntry = cq.from(Category.class);
        CriteriaQuery<Category> all = cq.select(rootEntry);
        TypedQuery<Category> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Product> getAllProducts() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> rootEntry = cq.from(Product.class);
        CriteriaQuery<Product> all = cq.select(rootEntry);
        TypedQuery<Product> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
