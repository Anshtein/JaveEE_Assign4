/********************************************************************egg***m******a**************n************
 * File: Product.java
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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 * The Product class is for accessing, persisting, and managing data
 * associated with Product entity.
 * It demonstrates OneToOne relationship with Choice table,
 * and ManyToMany relationship with with Category table
 */
@Entity
@EntityListeners({AuditListener.class})
public class Product extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * name of a product
     */
    protected String name;
    /**
     * price of a product
     */
    protected double price;    
    /**
     * list of categories
     */
    protected List<Category> categories;

    /**
     * default constructor
     */
    public Product() {
        super();
    }


    /**
     * Map OneToOne and get the child entity
     * @return Choice choice
     */
    //    @OneToOne(orphanRemoval=true, mappedBy="product", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    //    public Choice getChoice() {
    //        return choice;
    //    }
    //
    //    public void setChoice(Choice choice) {
    //        this.choice = choice;
    //    }

    /**
     * getter for the list of categories
     * annotated with ManyToMany to represent relationship with Category table
     * using an intermediary entity CATEGORY_PROD for the join table
     * @return list of Category
     */
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="CATEGORY_PROD",
    joinColumns=@JoinColumn(name="PROD_ID"),
    inverseJoinColumns=@JoinColumn(name="CATEGORY_ID"))
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * sets list of categories
     * @param categories
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * gets product name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * sets product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets product price
     * @return double price
     */
    public double getPrice() {
        return price;
    }

    /**
     * sets product price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
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
        .append("Product [SKU=")
        .append(id)
        .append(", name=")
        .append(name)
        .append(", price=")
        .append(String.valueOf(price))
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
