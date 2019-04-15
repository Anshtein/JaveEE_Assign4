/********************************************************************egg***m******a**************n************
 * File: CartBean.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.algonquincollege.cst8277.models.Cart;

/**
 * Stateless session bean containing business logic associated with Cart entity 
 */
@Stateless
public class CartBean {

    /**
     * EntityManager injected into the bean
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    /**
     * adds cart
     * @param newCart
     * @return
     */
    public int addCart(Cart newCart) {
        em.persist(newCart);
        return newCart.getId(); 
    }

    /**
     * finds list of all carts
     * @return List of Cart
     */
    public List<Cart> getCartList() { 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
        cq.select(cq.from(Cart.class));
        return em.createQuery(cq).getResultList();   
    }

    /**
     * updates cart 
     * @param updatedCart
     * @return int cart id
     */
    public int updateCart(Cart updatedCart) { 
        em.merge(updatedCart);
        return updatedCart.getId();
    }

    /**
     * deletes cart
     * @param cart
     * @return true if success, false otherwise
     */
    public boolean deleteCart(Cart cart) { 
        if (!em.contains(cart)) {
            cart = em.merge(cart);
        }
        em.remove(cart);

        if(em.contains(cart))
            return false;
        else return true;   
    }

    /**
     * finds cart by id
     * @param id
     * @return Cart cart
     */
    public Cart getCartById(int id) {
        return em.find(Cart.class, id);
    }

    /**
     * finds list of carts by customer id
     * @param id
     * @return List of Cart
     */
    public List<Cart> getCartsByCustomerId(int id) {
        Query q = em.createNativeQuery("SELECT * FROM CART C WHERE OWNING_CUST_ID = ?");
        q.setParameter(1, id); 
        List<Cart> carts = q.getResultList();    
        return carts;
    }
 
}
