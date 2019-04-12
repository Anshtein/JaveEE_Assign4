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
public class Choice extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */

    protected int quantity;
    protected Cart cart;
    protected Product product;
    
    public Choice () {
        super();
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CART_ID")
    public Cart getCart() {
        return cart;
    }
    
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    @OneToOne(orphanRemoval=true, cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinColumn(name="PRODUCT_ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 
    

}
