package com.algonquincollege.cst8277.models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class CustomerContactTestSuite  {

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
    public void _01_test_all_Contacts() {
    	em = emf.createEntityManager();
    	
        EntityManager em = emf.createEntityManager();   			
    	TypedQuery<Contact> query = em.createQuery("SELECT e FROM Contact e", Contact.class);
    	List<Contact> results = query.getResultList();
    	assertEquals(3, results.size());
    	em.close();	    
    }
    
    /**
     * Test typed query
     */
    @Test
    public void _02_test_all_Contacts_with_contact() {
    	em = emf.createEntityManager();
    	
    	TypedQuery<Contact> tq = em.createQuery("SELECT c FROM Customer c WHERE c.contact IS NOT NULL", Contact.class);
    	List<Contact> Contacts = tq.getResultList();
    	
    	assertEquals(3, Contacts.size());
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
    public void _03_createContact() {
    	em = emf.createEntityManager();
    	  	     
        Contact newContact = new Contact();
    	newContact.setCity("newcity");
    	newContact.setEmail("a@b.com");
    	newContact.setPhone("1234567");
    	newContact.setPostalCode("NOT WAY");
    	newContact.setProvince("RJ");
    	newContact.setStreet("Street");
        
        em.getTransaction().begin();
        em.persist(newContact);
        em.getTransaction().commit();
    	
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
    	Root<Contact> root = cq.from(Contact.class);
    	cq.select(root).where(cb.equal(root.get("province"), "RJ"));
    	Query query = em.createQuery(cq);
    	Contact result = (Contact) query.getSingleResult();
    	
    	assertNotNull(result);	
    	em.close();	    
    }
    
    /**
     * Test update
     */
    @Test
    public void _04_test_updateContact() {
    	em = emf.createEntityManager();
    	
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        Root<Contact> root = cq.from(Contact.class);
        cq.select(root);
        cq.where(cb.equal(root.get(Contact_.province), "RJ"));
        Query query = em.createQuery(cq);
        List<Contact> result = query.getResultList();
    	
    	Contact contact = result.get(0);
    	contact.setProvince("SP");
    	em.getTransaction().begin();
    	em.merge(contact);
    	em.getTransaction().commit();
    	
        cq.where(cb.equal(root.get(Contact_.province), "SP"));
        query = em.createQuery(cq);
    	Contact updated = (Contact) query.getSingleResult();
    	
    	
    	assertEquals("SP", updated.getProvince());
    	em.close();	    
    }
    
    
    /**
     * Test delete
     */
    @Test
    public void _05_test_assignContact() {
    	em = emf.createEntityManager();
    	
    	EntityManager em = emf.createEntityManager();
    	
    	Customer customer = em.find(Customer.class, 4);
    	TypedQuery<Contact> tq = em.createQuery("SELECT c FROM Contact c WHERE c.province = 'SP'", Contact.class);
    	List<Contact> contacts = tq.getResultList();
    	Contact contact = contacts.get(0);
    	
    	customer.setContact(contact);
    	em.getTransaction().begin();
    	em.persist(customer);
    	em.getTransaction().commit();
    	
    	customer = em.find(Customer.class, 4);
    	contact = customer.getContact();
    	assertEquals("SP", contact.getProvince());
    	
    	customer.setContact(null);
    	em.getTransaction().begin();
    	em.persist(customer);
    	em.getTransaction().commit();
    	em.close();
    	
    }  
    
    /**
     * Test delete
     */
    @Test
    public void _06_test_deleteContact() {
    	em = emf.createEntityManager();
    	
    	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Contact c where c.province = 'SP'").executeUpdate();
    	em.getTransaction().commit();
    	
    	TypedQuery<Contact> tq = em.createQuery("SELECT c FROM Contact c WHERE c.province = 'SP'", Contact.class);
    	List<Contact> result = tq.getResultList();
    	assertEquals(0, result.size());
    	em.close();	    
    }  
    
   
}
