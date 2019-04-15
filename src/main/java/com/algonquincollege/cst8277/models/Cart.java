/********************************************************************egg***m******a**************n************
 * File: Cart.java
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
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * The Cart class is for accessing, persisting, and managing data
 * associated with Cart entity.
 * It demonstrates OneToMany and ManyToOne relationship
 * with two other tables: Choice and Customer
 */
@Entity
@EntityListeners({AuditListener.class})
public class Cart extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;    
    
    /**
     * list of customer choices
     */
    protected List<Choice> choices;
    /**
     * Customer object
     */
    protected Customer customer;
    
    /**
     * default constructor
     */
    public Cart() {
        super();
    }
    /**
     * gets choices list, annotated with oneToMany relationship
     * @return list of choices
     */
    @JsonbTransient
    @OneToMany(mappedBy="cart", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Choice> getChoices() {
        return choices;
    }  
    
    /**
     * sets choices list
     * @param choices
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
  
    /**
     * gets Customer object, 
     * annotated with ManyToOne representing relationship with Customer table
     * @return Customer customer
     */
    @JsonbTransient
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNING_CUST_ID")
    public Customer getCustomer() {
        return customer;
    }

    /**
     * sets Customer
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * returns a unique integer value for the object at runtime
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * verifies the equality of two objects
     */
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

    /**
     * returns the string representation of the object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Contact [id=")
            .append(id)                              
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
