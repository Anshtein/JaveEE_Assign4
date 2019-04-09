package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@EntityListeners({AuditListener.class})
public class Invoice extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    protected Cart cart;
    protected Customer customer;
    
    
    /**
     * Map OneToOne and get the child entity
     * @return Cart cart
     */
    @OneToOne(orphanRemoval=true, cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinColumn(name="CART_ID")
    public Cart getCart() {
        return cart;
    }

    
    /**
     * Map ManyToOne and get Customer instance
     * @return
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUST_ID")
    public Customer getCustomer() {
        return customer;
    }


 

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    

}
