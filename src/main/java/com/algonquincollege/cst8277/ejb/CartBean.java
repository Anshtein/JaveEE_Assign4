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


@Stateless
public class CartBean {

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    public int addCart(Cart newCart) {
        em.persist(newCart);
        return newCart.getId(); 
    }

    public List<Cart> getCartList() { 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
        cq.select(cq.from(Cart.class));
        return em.createQuery(cq).getResultList();   
    }

    public int updateCart(Cart updatedCart) { 
        em.merge(updatedCart);
        return updatedCart.getId();
    }

    public boolean deleteCart(Cart cart) {
        
        if (!em.contains(cart)) {
            cart = em.merge(cart);
        }
        em.remove(cart);
        
        if(em.contains(cart))
            return false;
        else return true;   
    }

    public Cart getCartById(int id) {
        return em.find(Cart.class, id);
    }

    public List<Cart> getCartsByCustomerId(int id) {
        Query q = em.createNativeQuery("SELECT * FROM CART C WHERE OWNING_CUST_ID = ?");
        q.setParameter(1, id); 
        List<Cart> carts = q.getResultList();    
        return carts;
    }


}
