/********************************************************************egg***m******a**************n************
 * File: CustomerCartTestSuite.java
 * Course materials (19W) CST 8277
 * @author Mike Norman
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 *
 */

package com.algonquincollege.cst8277.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
public class CustomerCartTestSuite {
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
    public void _01_test_all_Carts() {
    	em = emf.createEntityManager();
    	
        EntityManager em = emf.createEntityManager();   			
    	TypedQuery<Cart> query = em.createQuery("SELECT e FROM Cart e", Cart.class);
    	List<Cart> results = query.getResultList();
    	assertEquals(3, results.size());
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
    public void _02_createCart() {
    	em = emf.createEntityManager();
    	
    	TypedQuery<Customer> tq = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = 'TEST4'", Customer.class);
    	Customer customer = tq.getSingleResult();
    	
    	  	     
        Cart newCart = new Cart();
    	newCart.setCustomer(customer);
    	newCart.setChoices(null);
    	em.getTransaction().begin();
        em.persist(newCart);
        em.getTransaction().commit();
    	
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
    	Root<Cart> root = cq.from(Cart.class);
    	cq.select(root);
    	Query query = em.createQuery(cq);
    	List<Cart> result = query.getResultList();
    	
    	assertNotEquals(3, result.size());
    	em.close();	    
    }
    
    /**
     * Test update
     */
    @Test
    public void _03_test_updateCart() {
    	em = emf.createEntityManager();
    	
    	TypedQuery<Customer> tq = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = 'TEST3'", Customer.class);
    	Customer customer = tq.getSingleResult();
    	
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
    	Root<Cart> root = cq.from(Cart.class);
    	cq.select(root);
    	Query query = em.createQuery(cq);
    	List<Cart> result = query.getResultList();
    	
    	Cart cart = result.get(3);
    	
    	cart.setCustomer(customer);
    	
    	em.getTransaction().begin();
        em.persist(cart);
        em.getTransaction().commit();
    	
    	TypedQuery<Cart> tqc = em.createQuery("SELECT c FROM Cart c", Cart.class);
    	List<Cart> carts = tqc.getResultList();
	  	
    	Cart updated = carts.get(3);
    	
    	assertEquals("TEST3", updated.getCustomer().getLastName());
    	em.close();	    
    }
    
    
    
    /**
     * Test delete
     */
    @Test
    public void _04_test_deleteCart() {
    	em = emf.createEntityManager();
    	
    	Cart cart = em.find(Cart.class, 4);
    	
        em.getTransaction().begin();
        em.remove(cart);
    	em.getTransaction().commit();
    	
    	TypedQuery<Cart> tq = em.createQuery("SELECT c FROM Cart c", Cart.class);
    	List<Cart> result = tq.getResultList();
    	assertEquals(3, result.size());
    	em.close();	    
    }  
    
   
}
