/********************************************************************egg***m******a**************n************
 * File: Choice.java
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

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * The Choice class is for accessing, persisting, and managing data
 * associated with Choice entity.
 * It demonstrates ManyToOne relationship with Cart table
 * and OneToOne relationship with Product table
 */
@Entity
@EntityListeners({AuditListener.class})
public class Choice extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */

    /**
     * choice quantity
     */
    protected int quantity;
    /**
     * reference to Cart object
     */
    protected Cart cart;
    /**
     * reference to Product object
     */
    protected Product product;
    
    /**
     * default constructor
     */
    public Choice () {
        super();
    }

    
    /**
     * gets Cart object, 
     * annotated with ManyToOne relationship with Cart table
     * @return cart
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CART_ID")
    public Cart getCart() {
        return cart;
    }
    
    /**
     * sets Cart object
     * @param cart
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    /**
     * gets Product object
     * annotated with OneToOne relationship with Product table
     * @return Product product
     */
    @OneToOne(orphanRemoval=true, cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinColumn(name="PRODUCT_ID")
    public Product getProduct() {
        return product;
    }

    /**
     * sets Product
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * gets choice quantity
     * @return int quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * sets quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   
    
    

}
