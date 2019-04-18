/********************************************************************egg***m******a**************n************
 * File: ChoiceProductTestSuite.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChoiceProductTestSuite {

    /**
     * EntityManagerFactory obj
     */
    static EntityManagerFactory emp;

    /**
     * set up method
     */
    @BeforeClass
    public static void oneTimeSetUp() {
        emp = Persistence.createEntityManagerFactory("shopping_cart_jee56");
    }

    /**
     * testing if choice was created
     */
    @Test
    public void _01_test_create_choice() {
        EntityManager em = emp.createEntityManager();
        Product product = em.find(Product.class, Integer.valueOf(1));
        Choice choice = new Choice();
        choice.setQuantity(376);
        choice.setProduct(product);

        em.getTransaction().begin();
        em.persist(choice);
        em.getTransaction().commit();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Choice> cq2 = cb.createQuery(Choice.class);
        Root<Choice> ch = cq2.from(Choice.class);
        cq2.select(ch);
        cq2.where(
                cb.equal(ch.get("quantity"), 376));
        TypedQuery<Choice> q2 = em.createQuery(cq2);
        List <Choice> cho = q2.getResultList(); 

        assertEquals(false, cho.isEmpty());
        em.close();
    }

    /**
     * testing if choice found is not empty as excpected
     */
    @Test
    public void _02_test_read_choices() {
        EntityManager em = emp.createEntityManager();             
        TypedQuery<Choice> query = em.createQuery("SELECT c FROM Choice c", Choice.class);
        List<Choice> results = query.getResultList();
        assertEquals(false, results.isEmpty());
        em.close();
    }

    /**
     * tesing if a choice found by product id is not empty
     */
    @Test
    public void _03_test_find_choice() {
        EntityManager em = emp.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        cb = em.getCriteriaBuilder();
        CriteriaQuery<Choice> cq = cb.createQuery(Choice.class);
        Root<Choice> choice = cq.from(Choice.class);
        Join <Product, Choice> pr = choice.join("product");

        cq.where(
                cb.equal(pr.get("id"), "1"));
        TypedQuery<Choice> q = em.createQuery(cq);
        List <Choice> ch = q.getResultList();

        assertEquals(false, ch.isEmpty());
        em.close();
    }

    /**
     * testing if choice was updated
     */
    @Test
    public void _04_test_update_choice() {
        EntityManager em = emp.createEntityManager();

        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Choice> update = cb.createCriteriaUpdate(Choice.class);
        Root <Choice> c = update.from(Choice.class);
        update.set("quantity", 1000);
        update.where(
                cb.greaterThan(c.get("quantity"), 1000));
        em.createQuery(update).executeUpdate();
        em.getTransaction().commit();

        cb = em.getCriteriaBuilder();
        CriteriaQuery<Choice> cq = cb.createQuery(Choice.class);
        Root<Choice> empl = cq.from(Choice.class);
        cq.select(empl); 
        cq.where(
                cb.equal(empl.get(Choice_.quantity), 10000));
        TypedQuery<Choice> q = em.createQuery(cq);
        List<Choice> list = q.getResultList();

        assertEquals(true, list.isEmpty());

        em.close();
    }

    /**
     * testing if choice was deleted
     */
    @Test
    public void _05_test_delete_choice() {
        EntityManager em = emp.createEntityManager();

        em.getTransaction().begin();
        em.createQuery("delete from Choice c where c.quantity = 3").executeUpdate();
        em.getTransaction().commit();

        TypedQuery<Choice> tq = em.createQuery("SELECT c FROM Choice c WHERE c.quantity = 3", Choice.class);
        List<Choice> result = tq.getResultList();
        assertEquals(0, result.size());

        em.close();
    }
}
