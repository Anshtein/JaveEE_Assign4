package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.Customer;

@Stateless
public class CartBean {
    
//    @Inject
//    protected BuildUser buildUser;
    
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;
    
    public List<Cart> getCartList() { 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
        cq.select(cq.from(Cart.class));
        return em.createQuery(cq).getResultList();   
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCart(Cart updatedCart) { 
        em.merge(updatedCart);
    }

    public Cart getCartById(int id) {
        return em.find(Cart.class, id);
    }
    
    public boolean addCart(Customer customer) {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            em.persist(cart);
            
            if(em.contains(cart))
                return true;
            else 
                return false;  
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
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Cart updateCart2(Cart customerWithUpdatedFields) {
        em.merge(customerWithUpdatedFields);
        return em.find(Cart.class, customerWithUpdatedFields.getId());
    }
    
    
    
    

}
