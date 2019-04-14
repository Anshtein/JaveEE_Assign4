package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.Contact;
import com.algonquincollege.cst8277.models.Customer;
import com.algonquincollege.cst8277.models.Product;

@Stateless
public class CartBean {

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
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
        else 
            return true;   
    }

    public Cart getCartById(int id) {
        return em.find(Cart.class, id);
    }

    ///?
    public List<Cart> getCartsByCustomerId(int id) {
        Query q = em.createNativeQuery("SELECT * FROM CART C WHERE OWNING_CUST_ID = ?");
        q.setParameter(1, id); 
        List<Cart> carts = q.getResultList();    
        return carts;
    }
    
    ///?
    public Cart getCartByCustomerId(int custId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
        Root<Cart> address = cq.from(Cart.class);
        Join<Cart, Customer> employee = address.join("employee");

        cq.where(
                cb.equal(employee.get("ID"), custId));
        TypedQuery<Cart> q = em.createQuery(cq);
        return q.getSingleResult();    
    }
    


}
