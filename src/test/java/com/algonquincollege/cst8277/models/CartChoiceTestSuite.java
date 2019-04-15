package com.algonquincollege.cst8277.models;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;


import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartChoiceTestSuite {
    
    static EntityManagerFactory emp;
    
    @BeforeClass
    public static void oneTimeSetUp() {
        emp = Persistence.createEntityManagerFactory("shopping_cart_jee56");
    }
    
    
    @Test
    public void _01_test_create_cart() {
        EntityManager em = emp.createEntityManager();
        Customer cust = em.find(Customer.class, Integer.valueOf(1));
        int cartCount = cust.getCarts().size();
        Cart cart = new Cart();
        cart.setCustomer(cust);
        List<Cart> list;
        if(cust.getCarts()!=null) {
            list = cust.getCarts();
        }else {
            list = new ArrayList<>();
        }
         
        list.add(cart);
        cust.setCarts(list);
        em.persist(cust);
        cust = em.find(Customer.class, Integer.valueOf(1));
        
        assertEquals(cartCount+1, cust.getCarts().size());
        em.close();
    
    }
    
    @Test
    public void _02_test_create_choice() {
        EntityManager em = emp.createEntityManager();
        Customer cust = em.find(Customer.class, Integer.valueOf(1));
        Choice choice = new Choice();
        choice.setQuantity(10);
        List<Cart> list = cust.getCarts();
        Cart cart = list.get(0);
        List<Choice> choiceList = cart.getChoices() == null ? new ArrayList<>() : cart.getChoices();
        int prevCount = choiceList.size();
        choiceList.add(choice);
        
        em.getTransaction().begin();
        em.persist(choice);
        em.persist(cart);
        em.persist(cust);
        em.getTransaction().commit();

        cust = em.find(Customer.class, Integer.valueOf(1));
        int nextCount = cust.getCarts().get(0).getChoices().size();
       
        assertEquals(prevCount+1, nextCount);
        em.close();  
    }
    
    @Test
    public void _03_test_read_choice() {
        EntityManager em = emp.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Choice> cq = cb.createQuery(Choice.class);
        Root<Choice> choice = cq.from(Choice.class);
        cq.select(choice); 
        cq.where(
                cb.equal(choice.get(Choice_.quantity), 10));
        TypedQuery<Choice> q = em.createQuery(cq);
        List<Choice> list  = q.getResultList();

        assertEquals(false, list.isEmpty());
        em.close();
    }
    
    @Test
    public void _04_test_update_choice() {
        EntityManager em = emp.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Choice> update = cb.createCriteriaUpdate(Choice.class);
        Root<Choice> choice = update.from(Choice.class);
        update.set("quantity", 219);
        update.where(
                cb.equal(choice.get(Choice_.quantity), 10));
        em.createQuery(update).executeUpdate();
        em.getTransaction().commit();

        cb = em.getCriteriaBuilder();
        CriteriaQuery<Choice> cq = cb.createQuery(Choice.class);
        Root<Choice> pr = cq.from(Choice.class);
        cq.select(pr); 
        cq.where(
                cb.equal(pr.get(Choice_.quantity), 219));
        TypedQuery<Choice> q = em.createQuery(cq);
        Choice updatedChoice = q.getResultList().get(0);

        assertEquals(219, updatedChoice.quantity);
        em.close();
    }
    
    @Test
    public void _05_test_delete_choice() {
        EntityManager em = emp.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Choice> cq = cb.createQuery(Choice.class);
        Root<Choice> choice = cq.from(Choice.class);
        cq.select(choice); 
        cq.where(
                cb.equal(choice.get(Choice_.quantity), 219));
        TypedQuery<Choice> q = em.createQuery(cq);
        List<Choice> selectedChoices  = q.getResultList();
        em.getTransaction().begin();
        for(Choice val:selectedChoices) {
            em.remove(val);
        }
        
        em.getTransaction().commit();
        cb = em.getCriteriaBuilder();
        cq = cb.createQuery(Choice.class);
        choice = cq.from(Choice.class);
        cq.select(choice); 
        cq.where(
                cb.equal(choice.get(Choice_.quantity), 219));
        q = em.createQuery(cq);
        selectedChoices  = q.getResultList();
        
        assertEquals(true, selectedChoices.isEmpty());
        em.close();
    }
    
    
}
