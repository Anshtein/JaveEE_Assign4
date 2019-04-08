package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@EntityListeners({AuditListener.class})
public class Cart extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    protected int quantity;
    protected List<Product> products;
    
    public Cart() {
        super();
    }
    
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="CART_PROD",
    joinColumns=@JoinColumn(name="CART_ID"),
    inverseJoinColumns=@JoinColumn(name="PROD_ID"))
    public List<Product> getProducts() {
        return products;
    }  
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cart)) {
            return false;
        }
        Cart other = (Cart)obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Contact [id=")
            .append(id)
            .append(", quantity=")
            .append(String.valueOf(quantity))                   
            .append(", version=")
            .append(version)
            .append(", created=")
            .append(audit.getCreatedDate())
            .append(", updated=")
            .append(audit.getUpdatedDate())
            .append("]")
        ;
        return builder.toString();
    }
    
}
