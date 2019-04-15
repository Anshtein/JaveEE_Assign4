package com.algonquincollege.cst8277.models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.algonquincollege.cst8277.ejb.BuildUser;
import com.algonquincollege.cst8277.ejb.SimpleBean;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerTestSuite  {

    public static EntityManagerFactory emf;
    public static EntityManager em;
    public static SimpleBean sb;
    protected BuildUser buildUser;


    @BeforeClass
    public static void oneTimeSetUp() {
    	emf = Persistence.createEntityManagerFactory("shopping_cart_jee56");
        sb = new SimpleBean();
    }

    /**
     * Test typed query
     */
    @Test
    public void _01_test_all_Customers_at_start() {
    	em = emf.createEntityManager();
    	
        EntityManager em = emf.createEntityManager();   			
    	TypedQuery<Customer> query = em.createQuery("SELECT e FROM Customer e", Customer.class);
    	List<Customer> results = query.getResultList();
    	assertEquals(4, results.size());
    	em.close();	    
    }
    
    /**
     * Test typed query
     */
    @Test
    public void _02_test_all_Customers_at_start() {
    	em = emf.createEntityManager();
    	
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
    	Root<Customer> root = cq.from(Customer.class);
    	cq.select(root);
    	Query query = em.createQuery(cq);
    	List<Customer> customers = query.getResultList();
    	
    	assertEquals(4, customers.size());
    	em.close();	    
    }

    // C-R-U-D lifecycle
    /*
     * Create
     */
    /**
     * Test create
     */
    @Test
    public void _03_createCustomer() {
    	em = emf.createEntityManager();
    	   	
    	String firstName = "junit";
    	String lastName = "test";
    	String userPassword = "temppwd";
    	     
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setUser(null);
        
        em.getTransaction().begin();
        em.persist(newCustomer);
        em.getTransaction().commit();
    	
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
    	Root<Customer> root = cq.from(Customer.class);
    	cq.select(root).where(cb.equal(root.get("firstName"), firstName));
    	Query query = em.createQuery(cq);
    	Customer result = (Customer) query.getSingleResult();
    	
    	assertNotNull(result);	
    	em.close();	    
    }
    
    /**
     * Test update
     */
    @Test
    public void _04_test_updateCustomer() {
    	em = emf.createEntityManager();
    	
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.select(root);
        cq.where(cb.equal(root.get(Customer_.firstName), "junit"));
        Query query = em.createQuery(cq);
        List<Customer> result = query.getResultList();
    	
    	Customer customer = result.get(0);
    	customer.setFirstName("newname");
    	em.getTransaction().begin();
    	em.merge(customer);
    	em.getTransaction().commit();
    	
        cq.where(cb.equal(root.get(Customer_.firstName), "newname"));
        query = em.createQuery(cq);
    	Customer updated = (Customer) query.getSingleResult();
    	
    	
    	assertEquals("newname", updated.getFirstName());
    	em.close();	    
    }
    
    /**
     * Test delete
     */
    @Test
    public void _05_test_deleteCustomer() {
    	em = emf.createEntityManager();
    	
    	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Customer c where c.firstName != 'CRUD'").executeUpdate();
    	
    	TypedQuery<Customer> tq = em.createQuery("SELECT c FROM Customer c WHERE c.user IS NULL", Customer.class);
    	List<Customer> result = tq.getResultList();
    	assertEquals(0, result.size());
    	em.close();	    
    }
    
    /**
     * Test SimpleBean
     */
    @Test
    public void _06_test_getCustomerList() { 	
    	List<Customer> result = sb.getCustomerList();
    	assertEquals(4, result.size());
    	em.close();	    
    }
    
    /**
     * Test SimpleBean
     */
    @Test
    public void _07_test_getCustomerById() { 	
    	Customer result = sb.getCustomerById(1);
    	assertEquals("TEST1", result.getLastName());
    	em.close();	    
    }
    
    /**
     * Test SimpleBean
     */
    @Test
    public void _08_test_checkCustomerUsernameId() { 	
    	boolean result = sb.checkCustomerUsernameId("CRUDTEST2", 2);
    	assertTrue(result);
    	em.close();	    
    }
    
    
}
