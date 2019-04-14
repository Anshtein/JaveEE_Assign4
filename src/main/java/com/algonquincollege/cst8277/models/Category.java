/********************************************************************egg***m******a**************n************
 * File: Category.java
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * The Category class is for accessing, persisting, and managing data
 * associated with Category entity.
 * It demonstrates ManyToMany relationship with Product table
 * through joined table Category_prod
 */
@Entity
@EntityListeners({AuditListener.class})
public class Category extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * name of a category
     */
    protected String name;
    /**
     * list of products
     */
    protected List<Product> products;
    /**
     * default constructor
     */
    public Category () {
        super();
    }

    /**
     * getter for the list of products
     * annotated with ManyToMany to represent relationship with Product table
     * using an intermediary entity CATEGORY_PROD for the join table
     * @return list of products
     */

    @JsonbTransient
    @ManyToMany(mappedBy="categories", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//    @JoinTable(name="CATEGORY_PROD",
//    joinColumns=@JoinColumn(name="CATEGORY_ID"),
//    inverseJoinColumns=@JoinColumn(name="PROD_ID"))    
    public List<Product> getProduct() {
        return products;
    }

    /**
     * sets list of products
     * @param products
     */
    public void setProduct(List<Product> products) {
        this.products = products;
    }

    /**
     * gets category name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * sets category name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
            .append(", category name=")
            .append(name)                   
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
